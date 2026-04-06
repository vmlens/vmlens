package com.vmlens.api.testbuilder.internal.runner;

import com.vmlens.api.testbuilder.internal.recording.RecordReadOnly;
import com.vmlens.api.testbuilder.internal.recording.RecordReadOnlyFactory;
import com.vmlens.api.testbuilder.internal.recording.RecordUpdate;

public class RecordReadOnlyFactoryMock implements RecordReadOnlyFactory<String> {

    private final String value;

    public RecordReadOnlyFactoryMock(String value) {
        this.value = value;
    }

    @Override
    public RecordUpdate<String> create() {
        return new RecordUpdateMock(value);
    }

    @Override
    public RecordReadOnly<String> createForAfterJoin() {
        return new RecordReadOnlyMock();
    }
}
