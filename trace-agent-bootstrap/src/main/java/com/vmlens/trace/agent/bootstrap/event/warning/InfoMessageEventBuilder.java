package com.vmlens.trace.agent.bootstrap.event.warning;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.toArray;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class InfoMessageEventBuilder {

    private final TLinkedList<TLinkableWrapper<String>> text = new TLinkedList<>();

    public InfoMessageEventBuilder add(String line) {
        text.add(wrap(line));
        return this;
    }

    public InfoMessageEventBuilder add(Throwable exception) {
        text.add(wrap(exception.getMessage()));
        add(exception.getStackTrace());
        return this;
    }

    public InfoMessageEventBuilder add(StackTraceElement[] elements) {
        for (StackTraceElement element : elements) {
            text.add(wrap(element.toString()));
        }
        return this;
    }

    public InfoMessageEvent build() {
        return new InfoMessageEvent(toArray(String.class, text));
    }

}
