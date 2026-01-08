package com.vmlens.api.atomic.internal.wrapper;

import com.vmlens.api.atomic.internal.concurrent.ConcurrentCall;
import com.vmlens.api.atomic.internal.recording.RecordUpdate;
import com.vmlens.api.atomic.internal.recording.RecordUpdateFactory;

import java.util.function.Consumer;

import static com.vmlens.api.atomic.internal.wrapper.CreateLabel.createLabel;

public class ConsumerWrapper<CLASS_UNDER_TEST> implements RecordUpdateFactory<CLASS_UNDER_TEST> ,
        RecordUpdate<CLASS_UNDER_TEST> ,
        ConcurrentCall<CLASS_UNDER_TEST>   {

    private final Consumer<CLASS_UNDER_TEST> consumer;
    private final int addPosition;

    public ConsumerWrapper(Consumer<CLASS_UNDER_TEST> consumer, int addPosition) {
        this.consumer = consumer;
        this.addPosition = addPosition;
    }

    @Override
    public void executeForRecording(CLASS_UNDER_TEST classUnderTest) {
        consumer.accept(classUnderTest);
    }

    @Override
    public boolean execute(CLASS_UNDER_TEST classUnderTest) {
        consumer.accept(classUnderTest);
        return true;
    }

    @Override
    public String getLabel() {
        return createLabel(" addWrite", addPosition );
    }

    @Override
    public ConcurrentCall<CLASS_UNDER_TEST> build() {
        return this;
    }

    @Override
    public RecordUpdate<CLASS_UNDER_TEST> create() {
        return this;
    }
}
