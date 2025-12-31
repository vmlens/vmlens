package com.vmlens.report.dominatortree;

public class UIStateElement {

    private final String label;
    private final UIStateElementSortKey uiStateElementSortKey;

    public UIStateElement(String label, 
                          UIStateElementSortKey uiStateElementSortKey) {
        this.label = label;
        this.uiStateElementSortKey = uiStateElementSortKey;
    }

    public String label() {
        return label;
    }

    public String idLabel() {
        return uiStateElementSortKey.idLabel();
    }

}
