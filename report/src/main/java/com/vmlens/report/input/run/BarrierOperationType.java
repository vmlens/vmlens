package com.vmlens.report.input.run;

public enum BarrierOperationType {
    
    NOTIFY("Notify") , WAIT_ENTER("Wait Enter") , WAIT_EXIT("Wait Exit") ;

    private final String text;

    BarrierOperationType(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
