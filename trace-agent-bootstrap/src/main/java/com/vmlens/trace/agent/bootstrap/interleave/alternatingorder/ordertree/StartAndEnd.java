package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class StartAndEnd {

    private final ListElementChoiceAlternative start;
    private final ListElementChoiceAlternative end;

    public StartAndEnd(ListElementChoiceAlternative start,
                       ListElementChoiceAlternative end) {
        this.start = start;
        this.end = end;
    }

    public ListElementChoiceAlternative start() {
        return start;
    }

    public ListElementChoiceAlternative end() {
        return end;
    }
}
