package com.vmlens.report.builder;

import com.vmlens.report.element.RunElement;
import com.vmlens.report.element.TestLoop;

import java.util.List;

public class LoopAndRun {

    private final TestLoop loop;
    private final List<RunElement> runElements;

    public LoopAndRun(TestLoop loop, List<RunElement> runElements) {
        this.loop = loop;
        this.runElements = runElements;
    }

    public TestLoop loop() {
        return loop;
    }

    public List<RunElement> runElements() {
        return runElements;
    }
}
