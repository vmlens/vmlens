package com.vmlens.report.input.run.objecthashcodemap;

public class MultipleThreadIndices implements ThreadIndices {

    private final int code;

    public MultipleThreadIndices(int code) {
        this.code = code;
    }

    @Override
    public ThreadIndices addThreadIndex(int newThreadIndex,ObjectHashCodeMap objectHashCodeMap) {
        return this;
    }

    @Override
    public String asUiString() {
        return "" + code;
    }
}
