package com.vmlens.expected.check;

import com.vmlens.report.assertion.EventForAssertion;

import java.util.Collections;
import java.util.List;


public class CheckLockEnterExit {

    public void check(List<EventForAssertion> eventList) {
        Collections.sort(eventList);
        boolean shouldBeEnter = true;

        for(EventForAssertion event : eventList) {
            IsEnterEvent isEnterEvent = new IsEnterEvent();
            event.eventForAssertionType().accept(isEnterEvent);
            if(isEnterEvent.isEnterEvent() != shouldBeEnter) {
                System.err.println(eventList);
                throw new RuntimeException("not enter followed by exit " + shouldBeEnter);
            }
            shouldBeEnter = ! shouldBeEnter;
        }

    }

}
