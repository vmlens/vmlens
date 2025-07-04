package com.vmlens.expected.check;

import com.vmlens.report.assertion.EventForAssertion;
import com.vmlens.report.assertion.OnEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BuildEventListMap implements OnEvent {

    private final Map<Integer, List<EventForAssertion>> loopIdToEventForAssertionList =
            new HashMap<>();

    @Override
    public void onEvent(EventForAssertion eventForAssertion) {
        List<EventForAssertion> list = loopIdToEventForAssertionList.computeIfAbsent(eventForAssertion.loopId(), k -> new LinkedList<>());
        list.add(eventForAssertion);
    }

    public Map<Integer, List<EventForAssertion>> build() {
        return loopIdToEventForAssertionList;
    }
}
