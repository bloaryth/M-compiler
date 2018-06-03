package Compiler2018.IR.IRStructure;

import Compiler2018.BackEnd.IIRVistor;

import java.util.LinkedHashMap;
import java.util.Map;

public class IRProgram {
    private final Map<String, IRClass> irClassMap = new LinkedHashMap<>();
    private final Map<String, IRFunction> irFunctionMap = new LinkedHashMap<>();    // processed String
    private final Map<String, StaticData> globalVarMap = new LinkedHashMap<>();
    private final Map<String, StaticData> staticStringMap = new LinkedHashMap<>();

    public IRClass getIRClass(String className){
        return irClassMap.get(className);
    }

    public void putIRClass(String className, IRClass irClass){
        irClassMap.put(className, irClass);
    }

    public IRFunction getIRFunction(String processedFunctionName){
        return irFunctionMap.get(processedFunctionName);
    }

    public void putIRFunction(String processedFunctionName, IRFunction irFunction) {
        irFunctionMap.put(processedFunctionName, irFunction);
    }

    public void putGlobalVar(String name, StaticData globalVar) {
        globalVarMap.put(name, globalVar);
    }

    public StaticData getGlobalVar(String label) {
        return globalVarMap.get(label);
    }

    public void putStaticString(String name, StaticData staticString) {
        staticStringMap.put(name, staticString);
    }

    public StaticData getStaticString(String label) {
        return staticStringMap.get(label);
    }

    public Map<String, IRClass> getIrClassMap() {
        return irClassMap;
    }

    public Map<String, IRFunction> getIrFunctionMap() {
        return irFunctionMap;
    }

    public Map<String, StaticData> getGlobalVarMap() {
        return globalVarMap;
    }

    public Map<String, StaticData> getStaticStringMap() {
        return staticStringMap;
    }


    // for malloc
//    public Integer getStaticSize(ClassType classType) {
//
//    }
//
//    public Integer getMallocSize(ClassType classType) {
//        if (classType.getAddrFlag() > 0) {
//            return 8;
//        } else {
//            switch (classType.getBaseType()) {
//                case "int":
//                case "bool":
//                    return 8;
//                case "string":
//                    return 8; // FIXME
//                default:
//                    return getIRClass(classType.getBaseType()).getSize();
//            }
//        }
//    }

//    public String toIRString() {
//        return irFunctionMap.get("_main").toIRString(); // FIXME
//    }

    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }
}
