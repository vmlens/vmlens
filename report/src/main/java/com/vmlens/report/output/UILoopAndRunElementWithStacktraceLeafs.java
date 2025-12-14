package com.vmlens.report.output;

import java.util.List;

public class UILoopAndRunElementWithStacktraceLeafs {

    private final UITestLoop uiTestLoop;
    private final List<UIRunElementWithStacktraceLeaf> uiRunElementWithStacktraceLeafs;
    private final List<UIWarning> uiWarnings;

    public UILoopAndRunElementWithStacktraceLeafs(UITestLoop uiTestLoop,
                                                  List<UIRunElementWithStacktraceLeaf> uiRunElementWithStacktraceLeafs,
                                                  List<UIWarning> uiWarnings) {
        this.uiTestLoop = uiTestLoop;
        this.uiRunElementWithStacktraceLeafs = uiRunElementWithStacktraceLeafs;
        this.uiWarnings = uiWarnings;
    }

    public UITestLoop uiTestLoop() {
        return uiTestLoop;
    }

    public List<UIRunElementWithStacktraceLeaf> uiRunElementWithStacktraceRoots() {
        return uiRunElementWithStacktraceLeafs;
    }

    public String name() {
        return uiTestLoop.name();
    }

    public List<UIWarning> uiWarnings() {
        return uiWarnings;
    }
}
