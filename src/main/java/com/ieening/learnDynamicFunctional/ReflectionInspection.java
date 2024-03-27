package com.ieening.learnDynamicFunctional;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionInspection {
    private Class<?> reflectionClass;

    public ReflectionInspection(String className) throws ClassNotFoundException {
        try {
            reflectionClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ReflectionInspection(Class<?> reflectionClass) {
        this.reflectionClass = reflectionClass;
    }

    @Override
    public String toString() {
        StringBuilder reflectionInspectionString = new StringBuilder();
        Class<?> superClass = reflectionClass.getSuperclass();
        String modifiers = Modifier.toString(reflectionClass.getModifiers());
        if (modifiers.length() > 0)
            reflectionInspectionString.append(modifiers + " ");
        reflectionInspectionString.append("class " + reflectionClass.getName());
        if (superClass != null && superClass != Object.class)
            reflectionInspectionString.append(" extends " + superClass.getName());
        reflectionInspectionString.append(" {\n");
        reflectionInspectionString.append(getConstructors());
        reflectionInspectionString.append("\n");
        reflectionInspectionString.append(getMethods());
        reflectionInspectionString.append("\n");
        reflectionInspectionString.append(getFields());
        reflectionInspectionString.append("}");
        return reflectionInspectionString.toString();
    }

    /**
     * get all constructors of a class
     * 
     */
    public String getConstructors() {
        StringBuilder constructorsString = new StringBuilder();

        Constructor<?>[] constructors = reflectionClass.getDeclaredConstructors();

        for (Constructor<?> c : constructors) {
            String name = c.getName();
            constructorsString.append("    ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0)
                constructorsString.append(modifiers + " ");
            constructorsString.append(name + "(");

            // get parameter types
            Class<?>[] paramTypes = c.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0)
                    constructorsString.append(", ");
                constructorsString.append(paramTypes[j].getName());
            }
            constructorsString.append(");\n");
        }
        return constructorsString.toString();
    }

    /**
     * get all methods of a class
     * 
     */
    public String getMethods() {
        StringBuilder methodsString = new StringBuilder();

        Method[] methods = reflectionClass.getDeclaredMethods();

        for (Method m : methods) {
            Class<?> retType = m.getReturnType();

            String name = m.getName();

            methodsString.append("    ");

            // get modifiers, return type and method name
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) {
                methodsString.append(modifiers + " ");
            }
            methodsString.append(retType.getName() + " " + name + "(");

            // get parameter types
            Class<?>[] paramTypes = m.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0)
                    methodsString.append(", ");
                methodsString.append(paramTypes[j].getName());
            }
            methodsString.append(");\n");
        }
        return methodsString.toString();
    }

    /**
     * Prints all fields of a class
     * 
     */
    public String getFields() {
        StringBuilder fieldsString = new StringBuilder();

        Field[] fields = reflectionClass.getDeclaredFields();

        for (Field f : fields) {
            Class<?> type = f.getType();
            String name = f.getName();
            fieldsString.append("    ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0)
                fieldsString.append(modifiers + " ");
            fieldsString.append(type.getName() + " " + name + ";\n");
        }
        return fieldsString.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ReflectionInspection rInspection = new ReflectionInspection("java.lang.String");
        System.out.println(rInspection.toString());
    }
}
