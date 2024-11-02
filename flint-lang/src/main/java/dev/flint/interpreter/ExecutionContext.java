package dev.flint.interpreter;

import java.util.HashMap;
import java.util.Map;

public class ExecutionContext {
    private Map<String, Object> variables = new HashMap<>();

    public void defineVariable(String name, Object value) {
        variables.put(name, value);
    }

    public void assignVariable(String name, Object value) {
        if (variables.containsKey(name)) {
            variables.put(name, value);
        } else {
            throw new RuntimeException("Variable " + name + " is not defined.");
        }
    }

    public Object getVariable(String name) {
        return variables.get(name);
    }
}

