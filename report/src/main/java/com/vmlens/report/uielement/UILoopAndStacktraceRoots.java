package com.vmlens.report.uielement;

import java.util.List;

public class UILoopAndStacktraceRoots {
    private final List<UIStacktraceRoot> rootNodes;
    private final List<UILoopAndRunElementWithStacktraceRoots> uiLoopAndRunElementsList;

    public UILoopAndStacktraceRoots(List<UIStacktraceRoot> rootNodes,
                                    List<UILoopAndRunElementWithStacktraceRoots> uiLoopAndRunElementsList) {
        this.rootNodes = rootNodes;
        this.uiLoopAndRunElementsList = uiLoopAndRunElementsList;
    }

    public List<UIStacktraceRoot> rootNodes() {
        return rootNodes;
    }

    public List<UILoopAndRunElementWithStacktraceRoots> uiLoopAndRunElementsList() {
        return uiLoopAndRunElementsList;
    }

}
