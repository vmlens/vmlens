package com.vmlens.report.overview;

import java.util.List;

public class UITestLoopAndWarning {

    private final UITestLoop uiTestLoop;
    private final List<UIWarning> uiWarnings;
    private final NeedsRunLink needsRunLink;

    public UITestLoopAndWarning(UITestLoop uiTestLoop,
                                List<UIWarning> uiWarnings,
                                NeedsRunLink needsRunLink) {
        this.uiTestLoop = uiTestLoop;
        this.uiWarnings = uiWarnings;
        this.needsRunLink = needsRunLink;
    }

    public UITestLoop uiTestLoop() {
        return uiTestLoop;
    }
    
    public void setLink(String link) {
        needsRunLink.setRunLink(link);
        uiTestLoop.setLink(link);
    }

    public String name() {
        return uiTestLoop.name();
    }

    public List<UIWarning> uiWarnings() {
        return uiWarnings;
    }
}
