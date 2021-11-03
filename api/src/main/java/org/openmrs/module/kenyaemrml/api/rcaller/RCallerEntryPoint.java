package org.openmrs.module.kenyaemrml.api.rcaller;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RCallerEntryPoint {

    public RCallerEntryPoint(){}
    public void rcaller() {
        try {

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine en = manager.getEngineByName("RCaller");
            // Create RCaller and RCode
            try {
                double[] a = new double[]{19.0, 17.0, 23.0};
                en.put("a", a);
                en.eval("sortedA <- sort(a)");
                double[] result = (double[]) en.get("sortedA");
                System.out.println(result[0]);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

