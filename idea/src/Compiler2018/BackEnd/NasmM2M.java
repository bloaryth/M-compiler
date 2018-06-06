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

public class NasmM2M implements IIRVistor {
    private Map<Register.PysicalRegister, Register> registerUseMap = new LinkedHashMap<>();
    public StringBuilder builder = new StringBuilder();
    private Integer currentRSP;
    private boolean globalVar;

    public StringBuilder getBuilder() {
        return builder;
    }

    private void stackToReg(Register register) {
        stackToReg(register.getAllocatedRegister(), register);
    }

    private void stackToReg(Register.PysicalRegister pysicalRegister, Register register) {
        builder.append("\tmov ");
        registerData(pysicalRegister, false);
        builder.append(", ");
        stackAddr(register);
        builder.append("\n");
    }

    private void regToStack(Register register) {
        regToStack(register.getAllocatedRegister(), register);
    }

    private void regToStack(Register.PysicalRegister pysicalRegister, Register register) {
        builder.append("\tmov ");
        stackAddr(register);
        builder.append(", ");
        registerData(pysicalRegister, false);
        builder.append("\n");
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
        currentRSP = 0;
        if (irFunction.getProcessedName().equals("_main")) {
            builder.append("main:\n");
        } else {
            builder.append(irFunction.getProcessedName()).append(":\n");
        }

        prologue();
        builder.append("\tsub rsp, ").append(-irFunction.getTotalOffset()).append("\n");
        saveCallee();
        copyParameter(irFunction.getParameterList());
        irFunction.getBasicBlockSet().forEach(x -> x.accept(this));
        builder.append("\n");
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
//            String[] split = irInstruction.toIRString().split("\n");
//            for (String aSplit : split) {
//                builder.append("\t;").append(aSplit).append("\n");
//            }
            irInstruction.accept(this);
        }
    }

    @Override
    public void visit(BinaryCalc ir) {
        ir.getDestination().setAllocatedRegister(Register.PysicalRegister.R12);
        ir.getLeftOperand().setAllocatedRegister(Register.PysicalRegister.R14);
        ir.getRightOperand().setAllocatedRegister(Register.PysicalRegister.R15);

//        stackToReg(ir.getDestination());
        stackToReg(ir.getLeftOperand());
        stackToReg(ir.getRightOperand());

        Register rightOperand;
        boolean rightStar;
        if (ir.getIntermediate() != null) {
            ir.getIntermediate().setAllocatedRegister(Register.PysicalRegister.R13);
            rightOperand = ir.getIntermediate();
            rightStar = false;
            move(rightOperand, false, ir.getRightOperand(), ir.isRightStar());
        } else {
            rightOperand = ir.getRightOperand();
            rightStar = ir.isRightStar();
        }

        switch (ir.getOprator()) {
            case ADD:
                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
                add(ir.getDestination(), false, rightOperand, rightStar);
                break;
            case SUB:
                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
                sub(ir.getDestination(), false, rightOperand, rightStar);
                break;
            case MUL:
                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
                imul(ir.getDestination(), false, rightOperand, rightStar);
                break;
            case DIV:
                setZero(Register.PysicalRegister.RDX);
                move(Register.PysicalRegister.RAX, false, ir.getLeftOperand(), ir.isLeftStar());
                idiv(rightOperand, rightStar);
                move(ir.getDestination(), false, Register.PysicalRegister.RAX, false);
                break;
            case MOD:
                setZero(Register.PysicalRegister.RDX);
                move(Register.PysicalRegister.RAX, false, ir.getLeftOperand(), ir.isLeftStar());
                idiv(rightOperand, rightStar);
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
                and(ir.getDestination(), false, rightOperand, rightStar);
                break;
            case BOR:
                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
                or(ir.getDestination(), false, rightOperand, rightStar);
                break;
            case XOR:
                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
                xor(ir.getDestination(), false, rightOperand, rightStar);
                break;
        }

        regToStack(ir.getDestination());
//        regToStack(ir.getLeftOperand());
//        regToStack(ir.getRightOperand());
    }

    @Override
    public void visit(Branch ir) {
//        ir.getCond().getLeftOperand().setAllocatedRegister(Register.PysicalRegister.R12);
//        ir.getCond().getRightOperand().setAllocatedRegister(Register.PysicalRegister.R13);
//
//        stackToReg(ir.getCond().getLeftOperand());
//        stackToReg(ir.getCond().getRightOperand());
//
//        Register rightOperand;
//        boolean rightStar;
//        if (ir.getCond().getIntermediate() != null) {
//            ir.getCond().getIntermediate().setAllocatedRegister(Register.PysicalRegister.R14);
//            rightOperand = ir.getCond().getIntermediate();
//            rightStar = false;
//            move(rightOperand, false, ir.getCond().getRightOperand(), ir.getCond().isRightStar());
//        } else {
//            rightOperand = ir.getCond().getRightOperand();
//            rightStar = ir.getCond().isRightStar();
//        }
//
//        cmp(ir.getCond().getLeftOperand(), ir.getCond().isLeftStar(), rightOperand, rightStar);
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

//        regToStack(ir.getCond().getLeftOperand());
//        regToStack(ir.getCond().getRightOperand());
    }

    @Override
    public void visit(Call ir) {    // FIXME
        Integer alignment = - currentRSP % 16;  // sub add rsp
        if (alignment == 8) {
            builder.append("\tsub rsp, ").append(alignment).append("\n");
        }
        saveCaller();
        prepareCallParameter(ir.getArgs());
//        if (ir.getRet() != null) {
//            loadIn(ir.getRet());
//        }
        builder.append("\tcall ").append(ir.getProcessedName()).append("\n");

        if (ir.getRet() != null) {
            ir.getRet().setAllocatedRegister(Register.PysicalRegister.RAX);
            regToStack(ir.getRet());
        }

        leaveCallParameter(ir.getArgs()); // use RAX

        if (alignment == 8) {
            builder.append("\tadd rsp, ").append(alignment).append("\n");
        }
    }

    @Override
    public void visit(Compare ir) {
//        ir.getDestination().setAllocatedRegister(Register.PysicalRegister.R12);
        ir.getLeftOperand().setAllocatedRegister(Register.PysicalRegister.R14);
        ir.getRightOperand().setAllocatedRegister(Register.PysicalRegister.R15);

//        stackToReg(ir.getDestination());
        stackToReg(ir.getLeftOperand());
        stackToReg(ir.getRightOperand());

        Register rightOperand;
        boolean rightStar;
        if (ir.getIntermediate() != null) {
            ir.getIntermediate().setAllocatedRegister(Register.PysicalRegister.R13);
            rightOperand = ir.getIntermediate();
            rightStar = false;
            move(rightOperand, false, ir.getRightOperand(), ir.isRightStar());
        } else {
            rightOperand = ir.getRightOperand();
            rightStar = ir.isRightStar();
        }

        cmp(ir.getLeftOperand(), ir.isLeftStar(), rightOperand, rightStar);
//        switch (ir.getOprator()) {
//            case EQ:
//                sete(ir.getDestination());
//                break;
//            case NE:
//                setne(ir.getDestination());
//                break;
//            case LT:
//                setl(ir.getDestination());
//                break;
//            case LE:
//                setle(ir.getDestination());
//                break;
//            case GT:
//                setg(ir.getDestination());
//                break;
//            case GE:
//                setge(ir.getDestination());
//                break;
//        }

//        regToStack(ir.getDestination());
//        regToStack(ir.getLeftOperand());
//        regToStack(ir.getRightOperand());
    }

    @Override
    public void visit(Jump ir) {
        jmp(ir.getJumpBlock());
    }

    @Override
    public void visit(Lea ir) {
        ir.getDestination().setAllocatedRegister(Register.PysicalRegister.R12);
        ir.getBase().setAllocatedRegister(Register.PysicalRegister.R13);

//        stackToReg(ir.getDestination());
        stackToReg(ir.getBase());
        if (ir.getPos() != null) {
            ir.getPos().setAllocatedRegister(Register.PysicalRegister.R14);
            stackToReg(ir.getPos());
        }

        lea(ir.getDestination(), ir.getBase(), ir.getPos(), ir.getOffset());

        regToStack(ir.getDestination());
//        regToStack(ir.getBase());
//        if (ir.getPos() != null) {
//            regToStack(ir.getPos());
//        }
    }

    @Override
    public void visit(Move ir) {
        ir.getLhs().setAllocatedRegister(Register.PysicalRegister.R12);
        ir.getRhs().setAllocatedRegister(Register.PysicalRegister.R13);

//        stackToReg(ir.getLhs());
        stackToReg(ir.getRhs());

        Register rightOperand;
        boolean rightStar;
        if (ir.getIntermediate() != null) {
            ir.getIntermediate().setAllocatedRegister(Register.PysicalRegister.R14);
            rightOperand = ir.getIntermediate();
            rightStar = false;
            move(rightOperand, rightStar, ir.getRhs(), ir.isRhsStar());
        } else {
            rightOperand = ir.getRhs();
            rightStar = ir.isRhsStar();
        }

        move(ir.getLhs(), ir.isLhsStar(), rightOperand, rightStar);

        regToStack(ir.getLhs());
//        regToStack(ir.getRhs());
    }

    @Override
    public void visit(MoveU ir) {
        ir.getLhs().setAllocatedRegister(Register.PysicalRegister.R12);

//        stackToReg(ir.getLhs());

        moveu(ir.getLhs(), ir.getRhs());

        regToStack(ir.getLhs());
    }

    @Override
    public void visit(Ret ir) {
        if (ir.getRet() != null) {
            ir.getRet().setAllocatedRegister(Register.PysicalRegister.RAX);
            stackToReg(ir.getRet());
        }

        restoreCallee();
        epilogue();
        builder.append("\tret\n");

//        regToStack(ir.getRet());
    }

    @Override
    public void visit(SelfInc ir) {
        ir.getDest().setAllocatedRegister(Register.PysicalRegister.R12);

        stackToReg(ir.getDest());

        inc(ir.getDest(), ir.isStar(), ir.getInc());

        regToStack(ir.getDest());
    }

    @Override
    public void visit(UnaryCalc ir) {
        ir.getDestination().setAllocatedRegister(Register.PysicalRegister.R12);
        ir.getOperand().setAllocatedRegister(Register.PysicalRegister.R13);

//        stackToReg(ir.getDestination());
        stackToReg(ir.getOperand());

        move(ir.getDestination(), false, ir.getOperand(), ir.isStar());
        switch (ir.getOprator()) {
            case NEG:
                neg(ir.getDestination(), false);
                break;
            case BITWISE_NOT:
                neg(ir.getDestination(), false);
                break;
        }

        regToStack(ir.getDestination());
//        regToStack(ir.getOperand());
    }

    @Override
    public void visit(Set ir) {
        ir.getDest().setAllocatedRegister(Register.PysicalRegister.R12);

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

        regToStack(ir.getDest());
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

    private void move(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
        builder.append("\tmov ");
        registerData(lhs, leftStar);
        builder.append(", ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void move(Register.PysicalRegister lhs, boolean leftStar, Register rhs, boolean rightStar) {
        builder.append("\tmov ");
        registerData(lhs, leftStar);
        builder.append(", ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void move(Register lhs, boolean leftStar, Register.PysicalRegister rhs, boolean rightStar) {
        builder.append("\tmov ");
        registerData(lhs, leftStar);
        builder.append(", ");
        registerData(rhs, rightStar);
        builder.append("\n");
    }

    private void move(Register.PysicalRegister lhs, Register.PysicalRegister rhs) {
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
        for (int i = registerList.size()-1; i > 5; i--) {
            registerList.get(i).setAllocatedRegister(Register.PysicalRegister.RAX);
            stackToReg(registerList.get(i));
            push(registerList.get(i));
        }
        if (registerList.size() > 0) {
            registerList.get(0).setAllocatedRegister(Register.PysicalRegister.RDI);
            stackToReg(registerList.get(0));
        }
        if (registerList.size() > 1) {
            registerList.get(1).setAllocatedRegister(Register.PysicalRegister.RSI);
            stackToReg(registerList.get(1));
        }
        if (registerList.size() > 2) {
            registerList.get(2).setAllocatedRegister(Register.PysicalRegister.RDX);
            stackToReg(registerList.get(2));
        }
        if (registerList.size() > 3) {
            registerList.get(3).setAllocatedRegister(Register.PysicalRegister.RCX);
            stackToReg(registerList.get(3));
        }
        if (registerList.size() > 4) {
            registerList.get(4).setAllocatedRegister(Register.PysicalRegister.R8);
            stackToReg(registerList.get(4));
        }
        if (registerList.size() > 5) {
            registerList.get(5).setAllocatedRegister(Register.PysicalRegister.R9);
            stackToReg(registerList.get(5));
        }
    }

    private void leaveCallParameter(List<Register> registerList) {
        for (int i = registerList.size()-1; i > 6; i--) {
            pop(registerList.get(i));
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
        }
    }

    private void saveCaller() {
        // Do nothing
    }

    private void saveCallee() {
        // Do nothing
    }

    private void restoreCallee() {
        // Do nothing
    }

    private void prologue() {
        builder.append("\tpush rbp\n");
        builder.append("\tmov rbp, rsp\n");
    }

    private void epilogue() {
        builder.append("\tleave\n");
    }

}
