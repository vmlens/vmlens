package com.vmlens.api.atomic.internal.concurrent;

public interface ConcurrentCall<CLASS_UNDER_TEST> {

    boolean execute(CLASS_UNDER_TEST classUnderTest);
    String getLabel();

}
