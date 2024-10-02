package com.vmlens.report.uielement;

import java.util.List;

public class UILoopsAndStacktraceLeafs {
    private final List<UIStacktraceLeaf> leafNodes;
    private final List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementsList;

    public UILoopsAndStacktraceLeafs(List<UIStacktraceLeaf> leafNodes,
                                     List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementsList) {
        this.leafNodes = leafNodes;
        this.uiLoopAndRunElementsList = uiLoopAndRunElementsList;
    }

    public List<UIStacktraceLeaf> rootNodes() {
        return leafNodes;
    }

    public List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementsList() {
        return uiLoopAndRunElementsList;
    }

}
