package com.vmlens.report.domain;

import java.util.List;

/**
 * root UIStacktraceElement -> link
 * List of UILoopAnRunElements
 */

public class UIStacktraceRootAndLoopAndRunElements {

    private final List<UIStacktraceElement> rootNodes;
    private final List<UILoopAndRunElements> uiLoopAndRunElementsList;

    public UIStacktraceRootAndLoopAndRunElements(List<UIStacktraceElement> rootNodes,
                                                 List<UILoopAndRunElements> uiLoopAndRunElementsList) {
        this.rootNodes = rootNodes;
        this.uiLoopAndRunElementsList = uiLoopAndRunElementsList;
    }

    public List<UIStacktraceElement> rootNodes() {
        return rootNodes;
    }

    public List<UILoopAndRunElements> uiLoopAndRunElementsList() {
        return uiLoopAndRunElementsList;
    }
}
