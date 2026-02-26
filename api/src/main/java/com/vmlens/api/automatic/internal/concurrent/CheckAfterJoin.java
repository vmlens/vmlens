package com.vmlens.api.automatic.internal.concurrent;

public interface CheckAfterJoin<CLASS_UNDER_TEST> {

    boolean readAndCheck(CLASS_UNDER_TEST classUnderTest);

}
