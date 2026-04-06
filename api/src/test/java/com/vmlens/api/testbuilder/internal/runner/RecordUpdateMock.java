package com.vmlens.api.testbuilder.internal.runner;

import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentCall;
import com.vmlens.api.testbuilder.internal.recording.RecordUpdate;

public class RecordUpdateMock implements RecordUpdate<String> {

    private final String value;

    public RecordUpdateMock(String value) {
        this.value = value;
    }

    @Override
    public void executeForRecording(String s) {

    }

    @Override
    public ConcurrentCall<String> build() {
        return new ConcurrentCallMock(value);
    }
}
