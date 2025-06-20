package com.vmlens.expected.check;

import com.vmlens.report.assertion.EventForAssertion;

import java.util.Collections;
import java.util.List;

public class CheckVolatileFieldAccessCount {

    public void check(List<EventForAssertion> eventList) {
        Collections.sort(eventList);
        CountVolatileFieldCount countVolatileFieldCount = new CountVolatileFieldCount();
        for(EventForAssertion event : eventList) {
            event.eventForAssertionType().accept(countVolatileFieldCount);
        }
        if(countVolatileFieldCount.count() != 3*5) {
            System.err.println(eventList);
            throw new RuntimeException("unexpected volatile field access");
        }
    }
}
