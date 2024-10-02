package com.vmlens.report.element;

public class TestLoop {

    private final int index;
    private final int loopId;
    private final String imagePath;
    private final String resultText;
    private final int count;

    public TestLoop(int index, int loopId, String imagePath, String resultText, int count) {
        this.index = index;
        this.loopId = loopId;
        this.imagePath = imagePath;
        this.resultText = resultText;
        this.count = count;
    }
}
