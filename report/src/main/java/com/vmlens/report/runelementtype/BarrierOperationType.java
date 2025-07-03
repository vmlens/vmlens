package com.vmlens.report.runelementtype;

public enum BarrierOperationType {
    
    NOTIFY("notify") , WAIT_ENTER("wait enter") , WAIT_EXIT("wait exit") ;


    private final String text;

    BarrierOperationType(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
