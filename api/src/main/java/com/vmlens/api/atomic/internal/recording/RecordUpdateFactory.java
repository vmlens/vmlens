package com.vmlens.api.atomic.internal.recording;

public interface RecordUpdateFactory<CLASS_UNDER_TEST> {

    RecordUpdate<CLASS_UNDER_TEST> create();

}
