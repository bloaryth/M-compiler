package Compiler2018.IR.IRValue;

public class Label extends AbstractValue {
    static Integer vid = 0;
    private final Integer id;
    private final String name;


    public Label(String name) {
        id = vid;
        vid += 1;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toIRString() {
        return name;
    }
}
