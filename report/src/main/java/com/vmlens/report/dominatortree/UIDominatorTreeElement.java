package com.vmlens.report.dominatortree;

import java.util.LinkedList;
import java.util.List;

public class UIDominatorTreeElement {
    
    private final String label;
    private final String css;
    private final List<UIStateElement> state = new LinkedList<>();
    

    public UIDominatorTreeElement(String label, String css) {
        this.label = label;
        this.css = css;
    }

    public void add(UIStateElement uiStateElement) {
        state.add(uiStateElement);
    }

    public String label() {
        return label;
    }

    public String css() {
        return css;
    }

    public List<UIStateElement> state() {
        return state;
    }
}
