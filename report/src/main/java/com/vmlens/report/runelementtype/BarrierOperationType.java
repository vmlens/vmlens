package com.vmlens.report.runelementtype;

public enum BarrierOperationType {
    
    NOTIFY("notify") , WAIT("wait") , WAIT_NOTIFY("waitNotify") ;


    private final String text;

    BarrierOperationType(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
