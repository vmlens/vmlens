package com.vmlens.api.atomic.internal.recording;

import com.vmlens.api.atomic.internal.concurrent.CheckAfterJoin;


public interface RecordReadOnly<CLASS_UNDER_TEST>   {

    void read(CLASS_UNDER_TEST classUnderTest);
    CheckAfterJoin<CLASS_UNDER_TEST> buildCheckAfterJoin();

}
