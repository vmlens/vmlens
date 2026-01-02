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
    
    public void setLinks(String runLink, String dominatorTreeLink,  String dominatorTreePrefix) {
        needsRunLink.setLinks(runLink,dominatorTreeLink,dominatorTreePrefix);
        uiTestLoop.setLinks(runLink,dominatorTreeLink);
    }

    public String name() {
        return uiTestLoop.name();
    }

    public List<UIWarning> uiWarnings() {
        return uiWarnings;
    }
}
