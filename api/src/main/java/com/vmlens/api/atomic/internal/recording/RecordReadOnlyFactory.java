package com.vmlens.api.atomic.internal.recording;

public interface RecordReadOnlyFactory<CLASS_UNDER_TEST>  extends RecordUpdateFactory<CLASS_UNDER_TEST> {

    RecordReadOnly<CLASS_UNDER_TEST> createForAfterJoin();

}
