package com.vmlens.api.atomic.internal.recording;

import com.vmlens.api.atomic.internal.concurrent.ConcurrentCall;

public interface RecordUpdate<CLASS_UNDER_TEST> {

    void executeForRecording(CLASS_UNDER_TEST classUnderTest);
    ConcurrentCall<CLASS_UNDER_TEST> build();

}
