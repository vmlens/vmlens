package com.vmlens.report.element;

public enum  MemoryAccessModifier {
    DataRace("data race ", ""), Volatile("", "volatile "), NonVolatile("", "");

    private final String postfix;
    private final String volatilePrefix;

    MemoryAccessModifier(String postfix, String volatilePrefix) {
        this.postfix = postfix;
        this.volatilePrefix = volatilePrefix;
    }

    public String postfix() {
        return postfix;
    }

    public String volatilePrefix() {
        return volatilePrefix;
    }
}
