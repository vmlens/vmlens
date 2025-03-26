package com.vmlens.trace.agent.bootstrap.exception;

public class TestBlockedException extends Exception {

    private final Message message;

    public TestBlockedException(Message message) {
        this.message = message;
    }

    public int id() {
        return message.id();
    }
}
