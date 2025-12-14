package com.vmlens.report.output;

import java.util.Collection;
import java.util.List;

public class UILoopsAndStacktraceLeafs {
    private final Collection<UIStacktraceLeaf> leafNodes;
    private final List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementsList;

    public UILoopsAndStacktraceLeafs(Collection<UIStacktraceLeaf> leafNodes,
                                     List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementsList) {
        this.leafNodes = leafNodes;
        this.uiLoopAndRunElementsList = uiLoopAndRunElementsList;
    }

    public Collection<UIStacktraceLeaf> rootNodes() {
        return leafNodes;
    }

    public List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementsList() {
        return uiLoopAndRunElementsList;
    }

}
