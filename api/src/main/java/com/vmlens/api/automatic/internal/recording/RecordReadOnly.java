package com.vmlens.api.automatic.internal.recording;

import com.vmlens.api.automatic.internal.concurrent.CheckAfterJoin;


public interface RecordReadOnly<CLASS_UNDER_TEST>   {

    void read(CLASS_UNDER_TEST classUnderTest);
    CheckAfterJoin<CLASS_UNDER_TEST> buildCheckAfterJoin();

}
