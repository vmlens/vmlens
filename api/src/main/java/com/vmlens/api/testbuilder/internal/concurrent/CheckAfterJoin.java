package com.vmlens.api.testbuilder.internal.concurrent;

public interface CheckAfterJoin<CLASS_UNDER_TEST> {

    boolean readAndCheck(CLASS_UNDER_TEST classUnderTest);

}
