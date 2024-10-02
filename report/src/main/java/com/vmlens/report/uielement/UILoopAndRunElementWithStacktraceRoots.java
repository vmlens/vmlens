package com.vmlens.report.uielement;

import java.util.List;

public class UILoopAndRunElementWithStacktraceRoots {

    private final UITestLoop uiTestLoop;
    private final List<UIRunElementWithStacktraceRoot> uiRunElementWithStacktraceRoots;

    public UILoopAndRunElementWithStacktraceRoots(UITestLoop uiTestLoop,
                                                  List<UIRunElementWithStacktraceRoot> uiRunElementWithStacktraceRoots) {
        this.uiTestLoop = uiTestLoop;
        this.uiRunElementWithStacktraceRoots = uiRunElementWithStacktraceRoots;
    }

    public UITestLoop uiTestLoop() {
        return uiTestLoop;
    }

    public List<UIRunElementWithStacktraceRoot> uiRunElementWithStacktraceRoots() {
        return uiRunElementWithStacktraceRoots;
    }
}
