package com.vmlens.types.specification;

/**
 * Transform argumentTypes to asmDescriptor
 * ToDo generics, Arrays, HowcTo handle return types
 *
 */

public class ToASMDescriptor {

    /**
     * Converts to a descriptor from a Java class name
     */
    public static String of(String classname) {
        if (classname.equals("void"))
            return "V";
        else if (classname.equals("int"))
            return "I";
        else if (classname.equals("byte"))
            return "B";
        else if (classname.equals("long"))
            return "J";
        else if (classname.equals("double"))
            return "D";
        else if (classname.equals("float"))
            return "F";
        else if (classname.equals("char"))
            return "C";
        else if (classname.equals("short"))
            return "S";
        else if (classname.equals("boolean"))
            return "Z";
        else
            return "L" + toJvmName(classname) + ";";
    }

    public static String toJvmName(String classname) {
        return classname.replace('.', '/');
    }


}
