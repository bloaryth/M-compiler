package Compiler2018.BackEnd;

import Compiler2018.IR.IRInstruction.*;
import Compiler2018.IR.IRStructure.*;
import Compiler2018.IR.IRValue.AbstractValue;
import Compiler2018.IR.IRValue.Immediate;
import Compiler2018.IR.IRValue.Label;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static Compiler2018.IR.IRValue.Register.PysicalRegister.*;

public class NasmColor implements IIRVistor {
    private boolean debug = false;

    private Map<Register.PysicalRegister, Register> registerUseMap = new LinkedHashMap<>();
    public StringBuilder builder = new StringBuilder();

    private Integer currentRSP;
    private boolean globalVar;
    private IRFunction currentFunction;
    private Register.PysicalRegister destPreserved = Register.PysicalRegister.RDX;
    //    private Register.PysicalRegister immediatePreserved = Register.PysicalRegister.R13;
    private Register.PysicalRegister leftOpPreserved = Register.PysicalRegister.RAX;
    private Register.PysicalRegister rightOpPreserved = Register.PysicalRegister.RCX;    // conhere with coloring

    public StringBuilder getBuilder() {
        return builder;
    }

    private void stackAddr(Register register) {
        builder.append("qword [rbp - ");
        builder.append(-register.getStackOffset());
        builder.append("]");
    }

    private void registerData(Register register, boolean star) {
        registerData(register.getAllocatedRegister(), star);
    }

    private void registerData(Register.PysicalRegister register, boolean star) {
        if (star) {
            builder.append("qword [");
        }
        builder.append(register.toString().toLowerCase());
        if (star) {
            builder.append("]");
        }
    }

    private void stackToReg(Register.PysicalRegister pReg, Register vReg) {
        builder.append("\tmov\t");
        registerData(pReg, false);
        builder.append(", ");
        stackAddr(vReg);
        builder.append("\n");
    }

    private void regToStack(Register vReg, Register.PysicalRegister pReg) {
        builder.append("\tmov\t");
        stackAddr(vReg);
        builder.append(", ");
        registerData(pReg, false);
        builder.append("\n");
    }

    @Override
    public void visit(IRProgram irProgram) {
        builder.append("global main\n\n");

        builder.append("SECTION .text\n\n");
        irProgram.getIrFunctionMap().forEach((x,y) -> y.accept(this));
        builder.append("SECTION .data\n\n");
        globalVar = true;
        irProgram.getGlobalVarMap().forEach((x, y) -> y.accept(this));
        globalVar = false;
        builder.append("SECTION .rodata.str1.1\n\n");
        irProgram.getStaticStringMap().forEach((x, y) -> y.accept(this));
    }

    @Override
    public void visit(IRFunction irFunction) {
        currentFunction = irFunction;
        currentRSP = 0;
        if (irFunction.getProcessedName().equals("_main")) {
            builder.append("main:\n");
        } else {
            builder.append(irFunction.getProcessedName()).append(":\n");
        }

        prologue();
        builder.append("\tsub rsp, ").append(-irFunction.getTotalOffset()).append("\n");
        List<Register> parameterList = irFunction.getParameterList();

        if (parameterList.size() > 2) { // 2
            if (parameterList.get(2).isAllocated()) {
                stackToReg(parameterList.get(2).getAllocatedRegister(), parameterList.get(2));
            }
        }
        if (parameterList.size() > 3) {
            if (parameterList.get(3).isAllocated()) {
                stackToReg(parameterList.get(3).getAllocatedRegister(), parameterList.get(3));
            }
        }   // in stack actually
        for (int i = 6; i < parameterList.size(); ++i) {    // 2 3 >= 6
            if (parameterList.get(i).isAllocated()) {
                stackToReg(parameterList.get(i).getAllocatedRegister(), parameterList.get(i));
            }
        }
        saveCallee();   // FIXME
        copyParameter(irFunction.getParameterList());
        irFunction.getBasicBlockSet().forEach(x -> x.accept(this));
        builder.append("\n");
        currentFunction = null;
    }

    @Override
    public void visit(IRClass irClass) {

    }

    @Override
    public void visit(StaticData irStaticData) {
        if (globalVar) {
            labelTranslate(irStaticData.getLabel());
            builder.append(":\n");
            builder.append("\tdq 0000000000000000H\n\n");
        } else {
            labelTranslate(irStaticData.getLabel());
            builder.append(":\n");
            char[] bytes = ((String) irStaticData.getVal()).toCharArray();
            builder.append("\tdb");
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] == '\\') {
                    switch (bytes[i + 1]) {
                        case 'n':
                            builder.append(" ").append(10).append(",");
                            break;
                        case '"':
                            builder.append(" ").append(34).append(",");
                            break;
                        case '\\':
                            builder.append(" ").append(92).append(",");
                            break;
                        default:
                            throw new RuntimeException("unprocessed \\");
                    }
                    i++;
                } else {
                    builder.append(" ").append((int) bytes[i]).append(",");
                }
            }
            builder.append(" 00\n\n");
        }
    }

    @Override
    public void visit(BasicBlock basicBlock) {
        builder.append(basicBlock.getProcessedName()).append(":\n");
        BasicBlock.Iter iter = new BasicBlock.Iter(basicBlock);
        while (iter.hasNext()) {
            AbstractIRInstruction irInstruction = iter.next();
                if (debug) {
                    String[] split = irInstruction.toIRString().split("\n");
                    for (String aSplit : split) {
                    builder.append("\t;").append(aSplit).append("\n");
                }
            }
            irInstruction.accept(this);
            builder.append("\n");
        }
    }

    @Override
    public void visit(BinaryCalc ir) {
        help(ir.getDestination(), destPreserved, false);
        help(ir.getLeftOperand(), leftOpPreserved, true);
        help(ir.getRightOperand(), rightOpPreserved, true);

        // dest != right
        switch (ir.getOprator()) {
            case ADD:
                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
                add(ir.getDestination(), false, ir.getRightOperand(), ir.isRightStar());
                break;
            case SUB:
                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
                sub(ir.getDestination(), false, ir.getRightOperand(), ir.isRightStar());
                break;
            case MUL:
                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
                imul(ir.getDestination(), false, ir.getRightOperand(), ir.isRightStar());
                break;
            case DIV:
                move(RAX, false, ir.getLeftOperand(), ir.isLeftStar());
                cqo();
                idiv(ir.getRightOperand(), ir.isRightStar());
                move(ir.getDestination(), false, RAX, false);
                break;
            case MOD:
                move(RAX, false, ir.getLeftOperand(), ir.isLeftStar());
                cqo();
                idiv(ir.getRightOperand(), ir.isRightStar());
                move(ir.getDestination(), false, Register.PysicalRegister.RDX, false);
                break;
            case LSH:
                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
                move(Register.PysicalRegister.RCX, false, ir.getRightOperand(), ir.isRightStar());
                sal(ir.getDestination(), false);
                break;
            case RSH:
                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
                move(Register.PysicalRegister.RCX, false, ir.getRightOperand(), ir.isRightStar());
                sar(ir.getDestination(), false);
                break;
            case BAND:
                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
                and(ir.getDestination(), false, ir.getRightOperand(), ir.isRightStar());
                break;
            case BOR:
                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
                or(ir.getDestination(), false, ir.getRightOperand(), ir.isRightStar());
                break;
            case XOR:
                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
                xor(ir.getDestination(), false, ir.getRightOperand(), ir.isRightStar());
                break;
        }

        unhelp(ir.getRightOperand(), false);
        unhelp(ir.getLeftOperand(), false);
        unhelp(ir.getDestination(), true);
    }

    @Override
    public void visit(Branch ir) {
        switch (ir.getCond().getOprator()) {
            case EQ:
                je(ir.getIfTrue());
                jne(ir.getIfFalse());
                break;
            case NE:
                jne(ir.getIfTrue());
                je(ir.getIfFalse());
                break;
            case LT:
                jl(ir.getIfTrue());
                jge(ir.getIfFalse());
                break;
            case LE:
                jle(ir.getIfTrue());
                jg(ir.getIfFalse());
                break;
            case GT:
                jg(ir.getIfTrue());
                jle(ir.getIfFalse());
                break;
            case GE:
                jge(ir.getIfTrue());
                jl(ir.getIfFalse());
                break;
        }
    }

    @Override
    public void visit(Call ir) {
        Integer alignment = - currentRSP % 16;  // sub add rsp
        if (alignment == 8) {
            builder.append("\tsub rsp, ").append(alignment).append("\n");
        }
        saveCaller();
        prepareCallParameter(ir.getArgs());
        builder.append("\tcall ").append(ir.getProcessedName()).append("\n");

        restoreCaller();

        if (ir.getRet() != null) {
            if (ir.getRet().isAllocated()) {
                move(ir.getRet().getAllocatedRegister(), RAX);
            } else {
                regToStack(ir.getRet(), RAX);
            }
        }

        leaveCallParameter(ir.getArgs()); // uses RAX

        if (alignment == 8) {
            builder.append("\tadd rsp, ").append(alignment).append("\n");
        }
    }

    @Override
    public void visit(Compare ir) { // FIXME
        help(ir.getLeftOperand(), leftOpPreserved, true);
        help(ir.getRightOperand(), rightOpPreserved, true);

        cmp(ir.getLeftOperand(), ir.isLeftStar(), ir.getRightOperand(), ir.isRightStar());

        unhelp(ir.getRightOperand(), false);
        unhelp(ir.getLeftOperand(), false);
    }

    @Override
    public void visit(Jump ir) {
        jmp(ir.getJumpBlock());
    }

    @Override
    public void visit(Lea ir) {
        help(ir.getDestination(), destPreserved, false);
        help(ir.getBase(), leftOpPreserved, true);
        help(ir.getPos(), rightOpPreserved, true);

        lea(ir.getDestination(), ir.getBase(), ir.getPos(), ir.getOffset());

        unhelp(ir.getPos(), false);
        unhelp(ir.getBase(), false);
        unhelp(ir.getDestination(), true);
    }

    @Override
    public void visit(Move ir) {
        help(ir.getLhs(), destPreserved, ir.isLhsStar());
        help(ir.getRhs(), leftOpPreserved, true);

        move(ir.getLhs(), ir.isLhsStar(), ir.getRhs(), ir.isRhsStar());

        unhelp(ir.getRhs(), !ir.isLhsStar());
        unhelp(ir.getLhs(), true);
    }

    @Override
    public void visit(MoveU ir) {
        help(ir.getLhs(), destPreserved, false);

        moveu(ir.getLhs(), ir.getRhs());

        unhelp(ir.getLhs(), true);
    }

    @Override
    public void visit(Ret ir) {
        if (ir.getRet() != null) {
            if (ir.getRet().isAllocated()) {
                move(RAX, ir.getRet().getAllocatedRegister());
            } else {
                stackToReg(RAX, ir.getRet());
            }
        }

        restoreCallee();
        epilogue();
        builder.append("\tret\n");
    }

    @Override
    public void visit(SelfInc ir) {
        help(ir.getDest(), destPreserved, true);

        inc(ir.getDest(), ir.isStar(), ir.getInc());

        unhelp(ir.getDest(), true);
    }

    @Override
    public void visit(UnaryCalc ir) {
        help(ir.getDestination(), destPreserved, false);
        help(ir.getOperand(), leftOpPreserved, true);

        move(ir.getDestination(), false, ir.getOperand(), ir.isStar());
        switch (ir.getOprator()) {
            case NEG:
                neg(ir.getDestination(), false);
                break;
            case BITWISE_NOT:
                not(ir.getDestination(), false);
                break;
        }

        unhelp(ir.getOperand(), false);
        unhelp(ir.getDestination(), true);

    }

    @Override
    public void visit(CSet ir) {
        help(ir.getDest(), destPreserved, false);

        switch (ir.getOp()) {
            case EQ:
                sete(ir.getDest());
                break;
            case NE:
                setne(ir.getDest());
                break;
            case LT:
                setl(ir.getDest());
                break;
            case LE:
                setle(ir.getDest());
                break;
            case GT:
                setg(ir.getDest());
                break;
            case GE:
                setge(ir.getDest());
                break;
        }

        unhelp(ir.getDest(), true);
    }

    private void help(Register vReg, Register.PysicalRegister pReg, boolean loadIn) {
        if (vReg == null) {
            return;
        }
        if (!vReg.isAllocated()) {
            vReg.setAllocatedRegister(pReg);
//            push(pReg); // for allocate
            if (loadIn) {
                stackToReg(pReg, vReg);
            }
        }
    }

    private void unhelp(Register vReg, boolean modified) {
        if (vReg == null) {
            return;
        }
        if (!vReg.isAllocated()) {
            if (modified) {
                regToStack(vReg, vReg.getAllocatedRegister());
            }
//            pop(vReg.getAllocatedRegister());   // for allocate
            vReg.setAllocatedRegister(null);
        }
    }

    private void labelTranslate(Label label) {
        builder.append("__Label");
        builder.append(label.getId());
    }

    private void blockTranslate(BasicBlock basicBlock) {
        builder.append(basicBlock.getProcessedName());
    }

    private void setZero(Register.PysicalRegister register){
        builder.append("\tmov ");
        registerData(register, false);
        builder.append(", ");
        builder.append(0);
        builder.append("\n");
    }

    private void cqo(){
        builder.append("\tcqo\n");
    }

    private void move(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
        if (lhs.getAllocatedRegister().equals(rhs.getAllocatedRegister()) && leftStar == rightStar) {
            return;
        }
        builder.append("\tmov ");
        registerData(lhs, leftStar);
        builder.append(", ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void move(Register.PysicalRegister lhs, boolean leftStar, Register rhs, boolean rightStar) {
        if (lhs.equals(rhs.getAllocatedRegister()) && leftStar == rightStar) {
            return;
        }
        builder.append("\tmov ");
        registerData(lhs, leftStar);
        builder.append(", ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void move(Register lhs, boolean leftStar, Register.PysicalRegister rhs, boolean rightStar) {
        if (lhs.getAllocatedRegister().equals(rhs) && leftStar == rightStar) {
            return;
        }
        builder.append("\tmov ");
        registerData(lhs, leftStar);
        builder.append(", ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void move(Register.PysicalRegister lhs, Register.PysicalRegister rhs) {
        if (lhs.equals(rhs)) {
            return;
        }
        builder.append("\tmov ");
        registerData(lhs, false);
        builder.append(", ");
        registerData(rhs, false);
        builder.append("\n");
    }

    private void moveu(Register lhs, AbstractValue value) {
        builder.append("\tmov ");
        registerData(lhs, false);
        builder.append(", ");
        if (value instanceof Label) {
            labelTranslate(((Label) value));
        } else if (value instanceof Immediate) {
            builder.append(((Immediate) value).getVal());
        } else {
            assert false;
        }
        builder.append("\n");
    }

    private void add(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
        builder.append("\tadd ");
        registerData(lhs, leftStar);
        builder.append(", ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void inc(Register lhs, boolean leftStar, Integer integer) {
        builder.append("\tadd ");
        registerData(lhs, leftStar);
        builder.append(", ");
        builder.append(integer);
        builder.append("\n");
    }

    private void sub(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
        builder.append("\tsub ");
        registerData(lhs, leftStar);
        builder.append(", ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void imul(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
        builder.append("\timul ");
        registerData(lhs, leftStar);
        builder.append(", ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void idiv(Register rhs, boolean rightStar) {
        builder.append("\tidiv ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void sal(Register lhs, boolean leftStar) {
        builder.append("\tsal ");
        registerData(lhs, leftStar);
        builder.append(", ");
        builder.append("cl");
        builder.append("\n");
    }

    private void sar(Register lhs, boolean leftStar) {
        builder.append("\tsar ");
        registerData(lhs, leftStar);
        builder.append(", ");
        builder.append("cl");
        builder.append("\n");
    }

    private void and(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
        builder.append("\tand ");
        registerData(lhs, leftStar);
        builder.append(", ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void or(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
        builder.append("\tor ");
        registerData(lhs, leftStar);
        builder.append(", ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void xor(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
        builder.append("\txor ");
        registerData(lhs, leftStar);
        builder.append(", ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void cmp(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
        builder.append("\tcmp ");
        registerData(lhs, leftStar);
        builder.append(", ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void je(BasicBlock block) {
        builder.append("\tje ");
        blockTranslate(block);
        builder.append("\n");
    }

    private void jne(BasicBlock block) {
        builder.append("\tjne ");
        blockTranslate(block);
        builder.append("\n");
    }

    private void jl(BasicBlock block) {
        builder.append("\tjl ");
        blockTranslate(block);
        builder.append("\n");
    }

    private void jle(BasicBlock block) {
        builder.append("\tjle ");
        blockTranslate(block);
        builder.append("\n");
    }

    private void jg(BasicBlock block) {
        builder.append("\tjg ");
        blockTranslate(block);
        builder.append("\n");
    }

    private void jge(BasicBlock block) {
        builder.append("\tjge ");
        blockTranslate(block);
        builder.append("\n");
    }

    private void sete(Register dest) {
        setZero(dest.getAllocatedRegister());
        builder.append("\tsete ");
        builder.append(reg2Byte(dest.getAllocatedRegister()));
        builder.append("\n");
    }

    private void setne(Register dest) { //
        setZero(dest.getAllocatedRegister());
        builder.append("\tsetne ");
        builder.append(reg2Byte(dest.getAllocatedRegister()));
        builder.append("\n");
    }

    private void setl(Register dest) {
        setZero(dest.getAllocatedRegister());
        builder.append("\tsetl ");
        builder.append(reg2Byte(dest.getAllocatedRegister()));
        builder.append("\n");
    }

    private void setle(Register dest) {
        setZero(dest.getAllocatedRegister());
        builder.append("\tsetle ");
        builder.append(reg2Byte(dest.getAllocatedRegister()));
        builder.append("\n");
    }

    private void setg(Register dest) { // FIXME
        setZero(dest.getAllocatedRegister());
        builder.append("\tsetg ");
        builder.append(reg2Byte(dest.getAllocatedRegister()));
        builder.append("\n");
    }

    private void setge(Register dest) {
        setZero(dest.getAllocatedRegister());
        builder.append("\tsetge ");
        builder.append(reg2Byte(dest.getAllocatedRegister()));
        builder.append("\n");
    }

    private String reg2Byte(Register.PysicalRegister register) {
        switch (register) {
            case RAX:
                return "al";
            case R10:
                return "r10b";
            case R11:
                return "r11b";
            case RDI:
                return "dil";
            case RSI:
                return "sil";
            case RDX:
                return "dl";
            case RCX:
                return "cl";
            case R8:
                return "r8b";
            case R9:
                return "r9b";
            case RBX:
                return "bl";
            case R12:
                return "r12b";
            case R13:
                return "r13b";
            case R14:
                return "r14b";
            case R15:
                return "r15b";
            default:
                throw new RuntimeException("unpermitted register");
        }
    }

    private void jmp(BasicBlock block) {
        builder.append("\tjmp ");
        blockTranslate(block);
        builder.append("\n");
    }

    private void lea(Register dest, Register base, Register pos, Integer offset) {
        builder.append("\tlea ");
        registerData(dest, false);
        builder.append(", [");
        registerData(base, false);
        if (pos != null) {
            builder.append(" + ");
            registerData(pos, false);
            builder.append("*8 ");
        }
        if (offset > 0) {
            builder.append(" + ");
            builder.append(offset);
        } else if (offset < 0){
            builder.append(" - ");
            builder.append(-offset);
        }
        builder.append("]\n");
    }

    private void neg(Register operand, boolean star) {
        builder.append("\tneg ");
        registerData(operand, star);
        builder.append("\n");
    }

    private void not(Register operand, boolean star) {
        builder.append("\tnot ");
        registerData(operand, star);
        builder.append("\n");
    }

    private void push(Register register) {
        builder.append("\tpush ");
        registerData(register, false);
        builder.append("\n");
        currentRSP -= 8;
    }

    private void push(Register.PysicalRegister register) {
        builder.append("\tpush ");
        registerData(register, false);
        builder.append("\n");
        currentRSP -= 8;
    }

    private void pop(Register register) {
        builder.append("\tpop ");
        registerData(register, false);
        builder.append("\n");
        currentRSP += 8;
    }

    private void pop(Register.PysicalRegister register) { // use carefully
        builder.append("\tpop ");
        registerData(register, false);
        builder.append("\n");
        currentRSP += 8;
    }

    private void prepareCallParameter(List<Register> registerList) {
        for (int i = 0; i < registerList.size(); ++i) {
            if (registerList.get(i).isAllocated()) {
                regToStack(registerList.get(i), registerList.get(i).getAllocatedRegister());
            }
        }
        for (int i = registerList.size()-1; i > 5; i--) {
            stackToReg(RAX, registerList.get(i));
            push(RAX);
        }
        if (registerList.size() > 0) {
            stackToReg(RDI, registerList.get(0));
        }
        if (registerList.size() > 1) {
            stackToReg(RSI, registerList.get(1));
        }
        if (registerList.size() > 2) {
            stackToReg(RDX, registerList.get(2));
        }
        if (registerList.size() > 3) {
            stackToReg(RCX, registerList.get(3));
        }
        if (registerList.size() > 4) {
            stackToReg(R8, registerList.get(4));
        }
        if (registerList.size() > 5) {
            stackToReg(R9, registerList.get(5));
        }
    }

    private void leaveCallParameter(List<Register> registerList) {
        for (int i = registerList.size()-1; i > 6; i--) {
            pop(RAX);   // FIXME
        }
        // no need to regToStack
    }

    private void copyParameter(List<Register> registerList) {
        if (registerList.size() > 0) {
            builder.append("\tmov qword [rbp ").append(registerList.get(0).getStackOffset()).append("], rdi\n");
        }
        if (registerList.size() > 1) {
            builder.append("\tmov qword [rbp ").append(registerList.get(1).getStackOffset()).append("], rsi\n");
        }
        if (registerList.size() > 2) {
            builder.append("\tmov qword [rbp ").append(registerList.get(2).getStackOffset()).append("], rdx\n");
        }
        if (registerList.size() > 3) {
            builder.append("\tmov qword [rbp ").append(registerList.get(3).getStackOffset()).append("], rcx\n");
        }
        if (registerList.size() > 4) {
            builder.append("\tmov qword [rbp ").append(registerList.get(4).getStackOffset()).append("], r8\n");
        }
        if (registerList.size() > 5) {
            builder.append("\tmov qword [rbp ").append(registerList.get(5).getStackOffset()).append("], r9\n");
        }
        for (int i = registerList.size()-1; i > 5; i--) {
            builder.append("\tmov rax, qword [rbp + ").append((8 * (i-6) + 16)).append("]\n");
            builder.append("\tmov qword [rbp ").append(registerList.get(i).getStackOffset()).append("], rax\n");
        }   // uses rax
    }

    private void saveCaller() {
//        push(RAX);
        push(RBX);
//        push(RCX);
//        push(RDX);
        push(RSI);
        push(RDI);
        push(R8);
        push(R9);
        push(R10);
        push(R11);
        push(R12);
        push(R13);
        push(R14);
        push(R15);
    }

    private void restoreCaller(){
        pop(R15);
        pop(R14);
        pop(R13);
        pop(R12);
        pop(R11);
        pop(R10);
        pop(R9);
        pop(R8);
        pop(RDI);
        pop(RSI);
//        pop(RDX);
//        pop(RCX);
        pop(RBX);
//        pop(RAX);
    }

    private void saveCallee() {
        Set<Register.PysicalRegister> calleeUsed = currentFunction.getCalleeUsed();
        calleeUsed.forEach(this::push);
    }

    private void restoreCallee() {
        Set<Register.PysicalRegister> calleeUsed = currentFunction.getCalleeUsed();
        calleeUsed.forEach(this::pop);
    }

    private void prologue() {
        builder.append("\tpush rbp\n");
        builder.append("\tmov rbp, rsp\n");
    }

    private void epilogue() {
        builder.append("\tleave\n");
    }

}
