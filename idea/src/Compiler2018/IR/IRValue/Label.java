package Compiler2018.IR.IRValue;

public class Label extends AbstractValue {
    static Integer vid;
//    private final Integer id;
    private final String name;


    public Label(String name) {
//        id = vid;
//        vid += 1;
//        if (name == null) {
//            name =
//        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toIRString() {
        return name;
    }
}
