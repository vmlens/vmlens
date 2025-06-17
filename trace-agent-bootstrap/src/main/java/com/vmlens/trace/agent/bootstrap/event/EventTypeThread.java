package com.vmlens.trace.agent.bootstrap.event;

public enum EventTypeThread {

    THREAD(1,"Thread") , THREAD_POOL(2, "Thread Pool");

    private final int code;
    private final String text;

    EventTypeThread(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int code() {
        return code;
    }

    public String text() {
        return text;
    }

    public static EventTypeThread fromCode(int code) {
        for (EventTypeThread t : values()) {
            if (t.code() == code) {
                return t;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
