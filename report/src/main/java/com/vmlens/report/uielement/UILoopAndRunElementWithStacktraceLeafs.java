package com.vmlens.report.uielement;

import java.util.List;

public class UILoopAndRunElementWithStacktraceLeafs {

    private final UITestLoop uiTestLoop;
    private final List<UIRunElementWithStacktraceLeaf> uiRunElementWithStacktraceLeafs;

    public UILoopAndRunElementWithStacktraceLeafs(UITestLoop uiTestLoop,
                                                  List<UIRunElementWithStacktraceLeaf> uiRunElementWithStacktraceLeafs) {
        this.uiTestLoop = uiTestLoop;
        this.uiRunElementWithStacktraceLeafs = uiRunElementWithStacktraceLeafs;
    }

    public UITestLoop uiTestLoop() {
        return uiTestLoop;
    }

    public List<UIRunElementWithStacktraceLeaf> uiRunElementWithStacktraceRoots() {
        return uiRunElementWithStacktraceLeafs;
    }
}
