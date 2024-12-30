package com.vmlens.trace.agent.bootstrap.event.warning;

public enum Message {
    TEST_BLOCKED_MESSAGE(1, "Test Blocked");

    private final int id;
    private final String text;

    Message(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public static Message of(int id) {
        for (Message value : values()) {
            if (value.id == id) {
                return value;
            }
        }
        throw new IllegalArgumentException("unknown id " + id);
    }

    public int id() {
        return id;
    }

    public String text() {
        return text;
    }

}
