package com.vmlens.trace.agent.bootstrap;

public class MemoryAccessType {

    public static final int IS_READ = 1;
    public static final int IS_WRITE = 2;
    public static final int IS_READ_WRITE = 3;


    public static boolean containsWrite(int operation) {
        return ((operation & IS_WRITE) == IS_WRITE);
    }

    public static boolean containsRead(int operation) {
        return ((operation & IS_READ) == IS_READ);
    }

    public static String asString(int operation) {
        if (operation == IS_READ) {
            return "Read";
        }
        if (operation == IS_WRITE) {
            return "Write";
        }
        if (operation == IS_READ_WRITE) {
            return "ReadWrite";
        }
        return "unknown";
    }
}
