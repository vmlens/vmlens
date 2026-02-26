package com.vmlens.api.automatic.internal.recording;

import com.vmlens.api.automatic.internal.concurrent.ConcurrentCall;

public interface RecordUpdate<CLASS_UNDER_TEST> {

    void executeForRecording(CLASS_UNDER_TEST classUnderTest);
    ConcurrentCall<CLASS_UNDER_TEST> build();

}
