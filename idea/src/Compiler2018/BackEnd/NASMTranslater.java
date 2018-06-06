//package Compiler2018.BackEnd;
//
//import Compiler2018.IR.IRInstruction.*;
//import Compiler2018.IR.IRStructure.*;
//import Compiler2018.IR.IRValue.AbstractValue;
//import Compiler2018.IR.IRValue.Immediate;
//import Compiler2018.IR.IRValue.Label;
//import Compiler2018.IR.IRValue.Register;
//import Compiler2018.Test;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//// rax rcx rdx do not allocate
//public class NASMTranslater implements IIRVistor{
//    private Map<Register.PysicalRegister, Register> registerUseMap = new LinkedHashMap<>();
//    public StringBuilder builder = new StringBuilder();
//    private Integer currentRSP;
//    private boolean globalVar;
//
//    public StringBuilder getBuilder() {
//        return builder;
//    }
//
//    private void moveToMem(Register register) {
//        builder.append("\t");
//        builder.append("mov qword [rbp - ").append(-register.getStackOffset()).append("], ");
//        registerTranslate(register, false);
//        builder.append("\n");
//    }
//
//    private void moveToReg(Register register) {
//        builder.append("\t");
//        builder.append("mov ");
//        registerTranslate(register, false);
//        builder.append(", qword [rbp - ").append(-register.getStackOffset()).append("]");
//        builder.append("\n");
//    }
//
//    private void loadIn(Register inReg) {
//        Register outReg = registerUseMap.get(inReg.getAllocatedRegister());
//        if (outReg == inReg) {
//            return;
//        }
//        if (outReg != null) {
//            moveToMem(outReg);
//        }
//        moveToReg(inReg);
//
//        registerUseMap.put(inReg.getAllocatedRegister(), inReg);
//    }
//
//    private void clear(Register.PysicalRegister register) {
//        Register in = registerUseMap.get(register);
//        if (in != null) {
//            moveToMem(in);
//        }
//
//        registerUseMap.remove(register);
//    }
//
//    public static String getTxt(String filePath) {
//        StringBuilder str = new StringBuilder();
//        try {
//            InputStreamReader reader = new InputStreamReader(Test.class.getResourceAsStream(filePath));
//            BufferedReader buffReader = new BufferedReader(reader);
//            String strTmp;
//            while ((strTmp = buffReader.readLine()) != null) {
//                str.append(strTmp + '\n');
//            }
//            buffReader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //        System.out.println (str.toString ());
//        return str.toString();
//    }
//
//    @Override
//    public void visit(IRProgram irProgram) {
//        builder.append(getTxt("../allinOne.asm"));
//
//        builder.append("global main\n\n");
//
//        builder.append("SECTION .text\n\n");
//        irProgram.getIrFunctionMap().forEach((x,y) -> y.accept(this));
//        builder.append("SECTION .data\n\n");
//        globalVar = true;
//        irProgram.getGlobalVarMap().forEach((x, y) -> y.accept(this));
//        globalVar = false;
//        builder.append("SECTION .rodata.str1.1\n\n");
//        irProgram.getStaticStringMap().forEach((x, y) -> y.accept(this));
//    }
//
//    @Override
//    public void visit(IRFunction irFunction) {
//        currentRSP = 0;
//        if (irFunction.getProcessedName().equals("_main")) {
//            builder.append("main:\n");
//        } else {
//            builder.append(irFunction.getProcessedName()).append(":\n");
//        }
//
//        prologue();
//        saveCallee();
//        copyParameter(irFunction.getParameterList());
//        irFunction.getBasicBlockSet().forEach(x -> x.accept(this));
//        builder.append("\n");
//    }
//
//    @Override
//    public void visit(IRClass irClass) {
//
//    }
//
//    @Override
//    public void visit(StaticData irStaticData) {
//        if (globalVar) {
//            labelTranslate(irStaticData.getLabel());
//            builder.append(":\n");
//            builder.append("\tdq 0000000000000000H\n\n");
//        } else {
//            labelTranslate(irStaticData.getLabel());
//            builder.append(":\n");
//            char[] bytes = ((String) irStaticData.getVal()).toCharArray();
//            builder.append("\tdb");
//            for (int i = 0; i < bytes.length; i++) {
//                builder.append(" ").append((int) bytes[i]).append(",");
//            }
//            builder.append(" 00\n\n");
//        }
//    }
//
//    @Override
//    public void visit(BasicBlock basicBlock) {
//        builder.append(basicBlock.getProcessedName()).append(":\n");
//        BasicBlock.Iter iter = new BasicBlock.Iter(basicBlock);
//        while (iter.hasNext()) {
//            iter.next().accept(this);
//        }
//    }
//
//    @Override
//    public void visit(BinaryCalc ir) {
//        loadIn(ir.getDestination());
//        loadIn(ir.getLeftOperand());
//        loadIn(ir.getRightOperand());
//
//        Register rightOperand;
//        boolean star; // FIXME name
//        if (ir.getIntermediate() != null) {
//            loadIn(ir.getIntermediate());
//            rightOperand = ir.getIntermediate();
//            star = false;
//            move(rightOperand, false, ir.getRightOperand(), ir.isRightStar());
//        } else {
//            rightOperand = ir.getRightOperand();
//            star = ir.isRightStar();
//        }
//
//        switch (ir.getOprator()) {
//            case ADD:
//                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isRightStar());
//                add(ir.getDestination(), false, rightOperand, star);
//                break;
//            case SUB:
//                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isRightStar());
//                sub(ir.getDestination(), false, rightOperand, star);
//                break;
//            case MUL:
//                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
//                imul(ir.getDestination(), false, rightOperand, star);
//                break;
//            case DIV:
//                clear(Register.PysicalRegister.RAX);
//                clear(Register.PysicalRegister.RDX);
//                setZero(Register.PysicalRegister.RDX);
//                move(Register.PysicalRegister.RAX, false, ir.getLeftOperand(), ir.isLeftStar());
//                idiv(rightOperand, star);
//                move(ir.getDestination(), false, Register.PysicalRegister.RAX, false);  // FIXME move lhs == rhs
//                break;
//            case MOD:
//                clear(Register.PysicalRegister.RAX);
//                clear(Register.PysicalRegister.RDX);
//                setZero(Register.PysicalRegister.RDX);
//                move(Register.PysicalRegister.RAX, false, ir.getLeftOperand(), ir.isLeftStar());
//                idiv(rightOperand, star);
//                move(ir.getDestination(), false, Register.PysicalRegister.RDX, false);
//                break;
//            case LSH:
//                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isRightStar());
//                clear(Register.PysicalRegister.RCX);
//                move(Register.PysicalRegister.RCX, false, ir.getRightOperand(), ir.isRightStar());
//                sal(ir.getDestination(), false);
//                break;
//            case RSH:
//                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isRightStar());
//                clear(Register.PysicalRegister.RCX);
//                move(Register.PysicalRegister.RCX, false, ir.getRightOperand(), ir.isRightStar());
//                sar(ir.getDestination(), false);
//                break;
//            case BAND:
//                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
//                and(ir.getDestination(), false, rightOperand, star);
//                break;
//            case BOR:
//                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
//                or(ir.getDestination(), false, rightOperand, star);
//                break;
//            case XOR:
//                move(ir.getDestination(), false, ir.getLeftOperand(), ir.isLeftStar());
//                xor(ir.getDestination(), false, rightOperand, star);
//                break;
//        }
//    }
//
//    @Override
//    public void visit(Branch ir) {
//        loadIn(ir.getCond().getLeftOperand());
//        loadIn(ir.getCond().getRightOperand());
//
//        Register rightOperand;
//        boolean star;   // FIXME name
//        if (ir.getCond().getIntermediate() != null) {
//            loadIn(ir.getCond().getIntermediate());
//            rightOperand = ir.getCond().getIntermediate();
//            star = false;
//            move(rightOperand, false, ir.getCond().getRightOperand(), ir.getCond().isRightStar());
//        } else {
//            rightOperand = ir.getCond().getRightOperand();
//            star = ir.getCond().isRightStar();
//        }
//
//        cmp(ir.getCond().getLeftOperand(), ir.getCond().isLeftStar(), rightOperand, star);
//        switch (ir.getCond().getOprator()) {
//            case EQ:
//                je(ir.getIfTrue());
//                jne(ir.getIfFalse());
//                break;
//            case NE:
//                jne(ir.getIfTrue());
//                je(ir.getIfFalse());
//                break;
//            case LT:
//                jl(ir.getIfTrue());
//                jge(ir.getIfFalse());
//                break;
//            case LE:
//                jle(ir.getIfTrue());
//                jg(ir.getIfFalse());
//                break;
//            case GT:
//                jg(ir.getIfTrue());
//                jle(ir.getIfFalse());
//                break;
//            case GE:
//                jge(ir.getIfTrue());
//                jl(ir.getIfFalse());
//                break;
//        }
//    }
//
//    @Override
//    public void visit(Call ir) {
//        Integer alignment = - currentRSP % 16;  // sub add rsp
//        if (alignment != 8) {
//            builder.append("\tsub ").append(alignment).append("\n");
//        }
//        saveCaller();
//        prepareCallParameter(ir.getArgs());
//        if (ir.getRet() != null) {
//            loadIn(ir.getRet());
//        }
//        builder.append("\tcall ").append(ir.getProcessedName()).append("\n");
//        leaveCallParameter(ir.getArgs());
//        if (alignment != 0) {
//            builder.append("\tadd ").append(alignment).append("\n");
//        }
//    }
//
//    @Override
//    public void visit(Compare ir) {
//        loadIn(ir.getLeftOperand());
//        loadIn(ir.getRightOperand());
//
//        Register rightOperand;
//        boolean star;   // FIXME name
//        if (ir.getIntermediate() != null) {
//            loadIn(ir.getIntermediate());
//            rightOperand = ir.getIntermediate();
//            star = false;
//            move(rightOperand, false, ir.getRightOperand(), ir.isRightStar());
//        } else {
//            rightOperand = ir.getRightOperand();
//            star = ir.isRightStar();
//        }
//
////        cmp(ir.getLeftOperand(), ir.isLeftStar(), rightOperand, star);
////        switch (ir.getOprator()) {
////            case EQ:
////                sete();
////                break;
////            case NE:
////                setne(ir.getDestination());
////                break;
////            case LT:
////                setl(ir.getDestination());
////                break;
////            case LE:
////                setle(ir.getDestination());
////                break;
////            case GT:
////                setg(ir.getDestination());
////                break;
////            case GE:
////                setge(ir.getDestination());
////                break;
////        }
//
//    }
//
//    @Override
//    public void visit(Jump ir) {
//        jmp(ir.getJumpBlock());
//    }
//
//    @Override
//    public void visit(Lea ir) {
//        loadIn(ir.getDestination());
//        loadIn(ir.getBase());
//        if (ir.getPos() != null) {
//            loadIn(ir.getPos());
//        }
//
//        lea(ir.getDestination(), ir.getBase(), ir.getPos(), ir.getOffset());
//    }
//
//    @Override
//    public void visit(Move ir) {
//        loadIn(ir.getLhs());
//        loadIn(ir.getRhs());
//
//        Register rightOperand;
//        boolean rightStar;
//        if (ir.getIntermediate() != null) {
//            rightOperand = ir.getIntermediate();
//            rightStar = false;
//            move(rightOperand, rightStar, ir.getRhs(), ir.isRhsStar());
//        } else {
//            rightOperand = ir.getRhs();
//            rightStar = ir.isRhsStar();
//        }
//
//        move(ir.getLhs(), ir.isLhsStar(), rightOperand, rightStar);
//    }
//
//    @Override
//    public void visit(MoveU ir) {
//        loadIn(ir.getLhs());
//        moveu(ir.getLhs(), ir.getRhs());
//    }
//
//    @Override
//    public void visit(Ret ir) {
//        restoreCallee();
//        epilogue();
//        builder.append("\tret\n");
//    }
//
//    @Override
//    public void visit(SelfInc ir) {
//        loadIn(ir.getDest());
//        inc(ir.getDest(), ir.isStar(), ir.getInc());
//    }
//
//    @Override
//    public void visit(UnaryCalc ir) {
//        loadIn(ir.getDestination());
//        loadIn(ir.getOperand());
//
//        move(ir.getDestination(), false, ir.getOperand(), ir.isStar());
//        switch (ir.getOprator()) {
//            case NEG:
//                neg(ir.getDestination(), false);
//                break;
//            case BITWISE_NOT:
//                neg(ir.getDestination(), false);
//                break;
//        }
//    }
//
//    private void registerTranslate(Register register, boolean star) {
//        registerTranslate(register.getAllocatedRegister(), star);
//    }
//
//    private void registerTranslate(Register.PysicalRegister register, boolean star) {
//        if (star) {
//            builder.append("qword [");
//        }
//        builder.append(register.toString().toLowerCase());
//        if (star) {
//            builder.append("]");
//        }
//    }
//
//    private void labelTranslate(Label label) {
//        builder.append("__");
//        builder.append(label.getName());
//    }
//
//    private void blockTranslate(BasicBlock basicBlock) {
//        builder.append(basicBlock.getProcessedName());
//    }
//
//    private void setZero(Register.PysicalRegister register){
//        builder.append("\tmov ");
//        registerTranslate(register, false);
//        builder.append(", ");
//        builder.append(0);
//        builder.append("\n");
//    }
//
//    private void move(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
//        builder.append("\tmov ");
//        registerTranslate(lhs, leftStar);
//        builder.append(", ");
//        registerTranslate(rhs, rightStar);
//        builder.append("\n");
//    }
//
//    private void move(Register.PysicalRegister lhs, boolean leftStar, Register rhs, boolean rightStar) {
//        builder.append("\tmov ");
//        registerTranslate(lhs, leftStar);
//        builder.append(", ");
//        registerTranslate(rhs, rightStar);
//        builder.append("\n");
//    }
//
//    private void move(Register lhs, boolean leftStar, Register.PysicalRegister rhs, boolean rightStar) {
//        builder.append("\tmov ");
//        registerTranslate(lhs, leftStar);
//        builder.append(", ");
//        registerTranslate(rhs, rightStar);
//        builder.append("\n");
//    }
//
//    private void move(Register.PysicalRegister lhs, Register.PysicalRegister rhs) {
//        builder.append("\tmov ");
//        registerTranslate(lhs, false);
//        builder.append(", ");
//        registerTranslate(rhs, false);
//        builder.append("\n");
//    }
//
//    private void moveu(Register lhs, AbstractValue value) {
//        builder.append("\tmov ");
//        registerTranslate(lhs, false);
//        builder.append(", ");
//        if (value instanceof Label) {
//            labelTranslate(((Label) value));
//        } else if (value instanceof Immediate) {
//            builder.append(((Immediate) value).getVal());
//        } else {
//            assert false;
//        }
//        builder.append("\n");
//    }
//
//    private void add(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
//        builder.append("\tadd ");
//        registerTranslate(lhs, leftStar);
//        builder.append(", ");
//        registerTranslate(rhs, rightStar);
//        builder.append("\n");
//    }
//
//    private void inc(Register lhs, boolean leftStar, Integer integer) {
//        builder.append("\tadd ");
//        registerTranslate(lhs, leftStar);
//        builder.append(", ");
//        builder.append(integer);
//        builder.append("\n");
//    }
//
//    private void sub(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
//        builder.append("\tsub ");
//        registerTranslate(lhs, leftStar);
//        builder.append(", ");
//        registerTranslate(rhs, rightStar);
//        builder.append("\n");
//    }
//
//    private void imul(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
//        builder.append("\timul ");
//        registerTranslate(lhs, leftStar);
//        builder.append(", ");
//        registerTranslate(rhs, rightStar);
//        builder.append("\n");
//    }
//
//    private void idiv(Register rhs, boolean rightStar) {
//        builder.append("\tidiv ");
//        registerTranslate(rhs, rightStar);
//        builder.append("\n");
//    }
//
//    private void sal(Register lhs, boolean leftStar) {
//        builder.append("\tsal ");
//        registerTranslate(lhs, leftStar);
//        builder.append(", ");
//        builder.append("cl");
//        builder.append("\n");
//    }
//
//    private void sar(Register lhs, boolean leftStar) {
//        builder.append("\tsar ");
//        registerTranslate(lhs, leftStar);
//        builder.append(", ");
//        builder.append("cl");
//        builder.append("\n");
//    }
//
//    private void and(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
//        builder.append("\tand ");
//        registerTranslate(lhs, leftStar);
//        builder.append(", ");
//        registerTranslate(rhs, rightStar);
//        builder.append("\n");
//    }
//
//    private void or(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
//        builder.append("\tor ");
//        registerTranslate(lhs, leftStar);
//        builder.append(", ");
//        registerTranslate(rhs, rightStar);
//        builder.append("\n");
//    }
//
//    private void xor(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
//        builder.append("\txor ");
//        registerTranslate(lhs, leftStar);
//        builder.append(", ");
//        registerTranslate(rhs, rightStar);
//        builder.append("\n");
//    }
//
//    private void cmp(Register lhs, boolean leftStar, Register rhs, boolean rightStar) {
//        builder.append("\tcmp ");
//        registerTranslate(lhs, leftStar);
//        builder.append(", ");
//        registerTranslate(rhs, rightStar);
//        builder.append("\n");
//    }
//
//    private void je(BasicBlock block) {
//        builder.append("\tje ");
//        blockTranslate(block);
//        builder.append("\n");
//    }
//
//    private void jne(BasicBlock block) {
//        builder.append("\tjne ");
//        blockTranslate(block);
//        builder.append("\n");
//    }
//
//    private void jl(BasicBlock block) {
//        builder.append("\tjl ");
//        blockTranslate(block);
//        builder.append("\n");
//    }
//
//    private void jle(BasicBlock block) {
//        builder.append("\tjle ");
//        blockTranslate(block);
//        builder.append("\n");
//    }
//
//    private void jg(BasicBlock block) {
//        builder.append("\tjg ");
//        blockTranslate(block);
//        builder.append("\n");
//    }
//
//    private void jge(BasicBlock block) {
//        builder.append("\tjge ");
//        blockTranslate(block);
//        builder.append("\n");
//    }
//
//    private void sete(Register dest) {
//        builder.append("\tsete ");
//        registerTranslate(dest, false);
//        builder.append("\n");
//    }
//
//    private void setne(Register dest) {
//        builder.append("\tsetne ");
//        registerTranslate(dest, false);
//        builder.append("\n");
//    }
//
//    private void setl(Register dest) {
//        builder.append("\tsetl ");
//        registerTranslate(dest, false);
//        builder.append("\n");
//    }
//
//    private void setle(Register dest) {
//        builder.append("\tsetle ");
//        registerTranslate(dest, false);
//        builder.append("\n");
//    }
//
//    private void setg(Register dest) {
//        builder.append("\tsetg ");
//        registerTranslate(dest, false);
//        builder.append("\n");
//    }
//
//    private void setge(Register dest) {
//        builder.append("\tsetge ");
//        registerTranslate(dest, false);
//        builder.append("\n");
//    }
//
//    private void jmp(BasicBlock block) {
//        builder.append("\tjmp ");
//        blockTranslate(block);
//        builder.append("\n");
//    }
//
//    private void lea(Register dest, Register base, Register pos, Integer offset) {
//        builder.append("\tlea ");
//        registerTranslate(dest, false);
//        builder.append(", [");
//        registerTranslate(base, false);
//        if (pos != null) {
//            builder.append(" + ");
//            registerTranslate(pos, false);
//            builder.append("*8 ");
//        }
//        if (offset > 0) {
//            builder.append(" + ");
//            builder.append(offset);
//        } else {
//            builder.append(" - ");
//            builder.append(-offset);
//        }
//        builder.append("]\n");
//    }
//
//    private void neg(Register operand, boolean star) {
//        builder.append("\tneg ");
//        registerTranslate(operand, star);
//        builder.append("\n");
//    }
//
//    private void not(Register operand, boolean star) {
//        builder.append("\tnot ");
//        registerTranslate(operand, star);
//        builder.append("\n");
//    }
//
//    private void push(Register register) {  // star is always false
//        loadIn(register);
//        builder.append("\tpush ");
//        registerTranslate(register, false);
//        builder.append("\n");
//        currentRSP -= 8;
//    }
//
//    private void pop(Register register) {
//        loadIn(register);
//        builder.append("\tpop ");
//        registerTranslate(register, false);
//        builder.append("\n");
//        currentRSP += 8;
//    }
//
//    private void push(Register.PysicalRegister register) { // use carefully
//        builder.append("\tpush ");
//        registerTranslate(register, false);
//        builder.append("\n");
//        currentRSP -= 8;
//    }
//
//    private void pop(Register.PysicalRegister register) { // use carefully
//        builder.append("\tpop ");
//        registerTranslate(register, false);
//        builder.append("\n");
//        currentRSP += 8;
//    }
//
//    private void prepareCallParameter(List<Register> registerList) {
//        for (int i = registerList.size()-1; i > 6; i--) {
//            push(registerList.get(i));
//        }
//        for (int i = 0; i < 5; i++) {
//            if (i < registerList.size()) {
//                loadIn(registerList.get(i));
//            }
//        }
//    }
//
//    private void leaveCallParameter(List<Register> registerList) {
//        for (int i = registerList.size()-1; i > 6; i--) {
//            pop(registerList.get(i));
//        }
//    }
//
//    private void copyParameter(List<Register> registerList) {
//        if (registerList.size() > 0) {
//            builder.append("\tmov [rbp ").append(registerList.get(0).getStackOffset()).append("], rdi\n");
//        }
//        if (registerList.size() > 1) {
//            builder.append("\tmov [rbp ").append(registerList.get(1).getStackOffset()).append("], rsi\n");
//        }
//        if (registerList.size() > 2) {
//            builder.append("\tmov [rbp ").append(registerList.get(2).getStackOffset()).append("], rdx\n");
//        }
//        if (registerList.size() > 3) {
//            builder.append("\tmov [rbp ").append(registerList.get(3).getStackOffset()).append("], rcx\n");
//        }
//        if (registerList.size() > 4) {
//            builder.append("\tmov [rbp ").append(registerList.get(4).getStackOffset()).append("], r8\n");
//        }
//        if (registerList.size() > 5) {
//            builder.append("\tmov [rbp ").append(registerList.get(5).getStackOffset()).append("], r9\n");
//        }
//        for (int i = registerList.size()-1; i > 6; i--) {
//            builder.append("\tmov rax, [rbp + ").append((8 * (i-6) + 16));
//            builder.append("\tmov [rbp ").append(registerList.get(i).getStackOffset()).append("], rax\n");
//        }
//    }
//
//    private void saveCaller() {
//        clear(Register.PysicalRegister.RAX);
//        clear(Register.PysicalRegister.R10);
//        clear(Register.PysicalRegister.R11);
//
//        clear(Register.PysicalRegister.RDI);
//        clear(Register.PysicalRegister.RSI);
//        clear(Register.PysicalRegister.RDX);
//        clear(Register.PysicalRegister.RCX);
//        clear(Register.PysicalRegister.R8);
//        clear(Register.PysicalRegister.R9);
//    }
//
//    private void saveCallee() {
//        push(Register.PysicalRegister.RBX);
//        push(Register.PysicalRegister.R12);
//        push(Register.PysicalRegister.R13);
//        push(Register.PysicalRegister.R14);
//        push(Register.PysicalRegister.R15);
//    }
//
//    private void restoreCallee() {
//        pop(Register.PysicalRegister.R15);
//        pop(Register.PysicalRegister.R14);
//        pop(Register.PysicalRegister.R13);
//        pop(Register.PysicalRegister.R12);
//        pop(Register.PysicalRegister.RBX);
//    }
//
//    private void prologue() {
//        push(Register.PysicalRegister.RBP);
//        move(Register.PysicalRegister.RBP, Register.PysicalRegister.RSP);
//    }
//
//    private void epilogue() {
//        builder.append("\tleave\n");
//    }
//}
