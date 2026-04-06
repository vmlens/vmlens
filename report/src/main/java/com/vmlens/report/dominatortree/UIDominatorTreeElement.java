package com.vmlens.report.dominatortree;

import java.util.LinkedList;
import java.util.List;

public class UIDominatorTreeElement {
    
    private final String label;
    private final String css;
    private final String link;

    public UIDominatorTreeElement(String label, String css, String link) {
        this.label = label;
        this.css = css;
        this.link = link;
    }

    public String label() {
        return label;
    }

    public String css() {
        return css;
    }

    public String link() {
        return link;
    }

    public boolean hasLink() {
        return link != null;
    }
}
