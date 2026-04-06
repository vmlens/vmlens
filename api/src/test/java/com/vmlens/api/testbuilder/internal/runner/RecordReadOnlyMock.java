package com.vmlens.api.testbuilder.internal.runner;

import com.vmlens.api.testbuilder.internal.concurrent.CheckAfterJoin;
import com.vmlens.api.testbuilder.internal.recording.RecordReadOnly;

public class RecordReadOnlyMock implements RecordReadOnly<String> {
    @Override
    public void read(String s) {

    }

    @Override
    public CheckAfterJoin<String> buildCheckAfterJoin() {
        return null;
    }
}
