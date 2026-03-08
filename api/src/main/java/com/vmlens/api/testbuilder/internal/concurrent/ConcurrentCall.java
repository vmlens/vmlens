package com.vmlens.api.testbuilder.internal.concurrent;

import com.vmlens.api.AllInterleavings;

public interface ConcurrentCall<CLASS_UNDER_TEST> {


    boolean execute(CLASS_UNDER_TEST classUnderTest, AllInterleavings allInterleavings);
    String getLabel();

}
