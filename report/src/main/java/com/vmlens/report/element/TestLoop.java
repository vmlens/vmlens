package com.vmlens.report.element;

public class TestLoop {


    private final int loopId;
    private final String resultText;
    private final String resultStyle;
    private final int count;


    public TestLoop(int loopId, String resultText, String resultStyle, int count) {
        this.loopId = loopId;
        this.resultText = resultText;
        this.resultStyle = resultStyle;
        this.count = count;
    }

    public int loopId() {
        return loopId;
    }

    public String resultText() {
        return resultText;
    }

    public int count() {
        return count;
    }

    public String resultStyle() {
        return resultStyle;
    }
}
