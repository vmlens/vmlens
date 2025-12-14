package com.vmlens.report.input.run.objecthashcodemap;

public class OneThreadIndex implements ThreadIndices {

    private final int threadIndex;

    public OneThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    @Override
    public ThreadIndices addThreadIndex(int newThreadIndex,ObjectHashCodeMap objectHashCodeMap) {
        if(threadIndex == newThreadIndex) {
            return this;
        }
        return new MultipleThreadIndices(objectHashCodeMap.newCode());
    }

    @Override
    public String asUiString() {
        return "";
    }
}
