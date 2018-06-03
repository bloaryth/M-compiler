package Compiler2018.IR.IRStructure;

import Compiler2018.BackEnd.IIRVistor;

import java.util.LinkedHashMap;
import java.util.Map;

public class IRClass {
    private Integer totalOffset = 0;
    private Map<String, Integer> heapOffsetMap = new LinkedHashMap<>();

    public Integer getHeapOffset(String name){
        return heapOffsetMap.get(name);
    }

    public void addHeapOffset(String name){
        heapOffsetMap.put(name, totalOffset);
        totalOffset += 8;   // all 8 bytes long
    }

    public Integer getSize() {
        return totalOffset + 8;
    }


    // for init
    private boolean cstr = false;

    private boolean hasCstr(){
        return cstr;
    }

    public void setCstr(boolean cstr) {
        this.cstr = cstr;
    }

    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }
}
