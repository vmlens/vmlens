package com.vmlens.api.testbuilder.internal.recording;

public interface RecordReadOnlyFactory<CLASS_UNDER_TEST>  extends RecordUpdateFactory<CLASS_UNDER_TEST> {

    RecordReadOnly<CLASS_UNDER_TEST> createForAfterJoin();

}
