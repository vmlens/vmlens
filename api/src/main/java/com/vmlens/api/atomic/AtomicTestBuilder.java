package com.vmlens.api.atomic;

import com.vmlens.api.atomic.internal.AtomicTestImpl;
import com.vmlens.api.atomic.internal.recording.RecordReadOnlyFactory;
import com.vmlens.api.atomic.internal.recording.RecordUpdateFactory;
import com.vmlens.api.atomic.internal.wrapper.CompareWithEquals;
import com.vmlens.api.atomic.internal.wrapper.ConsumerWrapper;
import com.vmlens.api.atomic.internal.wrapper.FunctionAndCompare;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.vmlens.api.atomic.internal.AutomaticTestTypes.READ;
import static com.vmlens.api.atomic.internal.AutomaticTestTypes.WRITE;

public class AtomicTestBuilder<CLASS_UNDER_TEST> {

    private static final AtomicInteger AUTOMATIC_TEST_ID = new AtomicInteger();

    private final Supplier<CLASS_UNDER_TEST> createClassUnderTest;
    private final List<RecordUpdateFactory<CLASS_UNDER_TEST>> writeList = new LinkedList<>();
    private final List<RecordReadOnlyFactory<CLASS_UNDER_TEST>> readOnlyList = new LinkedList<>();
    private final int atomicTestId;
    private int addWritePosition = 0;
    private int addUpdatePosition = 0;
    private int addReadOnlyPosition = 0;
    private int automaticTestMethodId = 0;

    public AtomicTestBuilder(Supplier<CLASS_UNDER_TEST> createClassUnderTest) {
        this.createClassUnderTest = createClassUnderTest;
        this.atomicTestId =  AUTOMATIC_TEST_ID.getAndIncrement();
    }

    public <READ_VALUE> AtomicTestBuilder<CLASS_UNDER_TEST> addReadOnly(Function<CLASS_UNDER_TEST,READ_VALUE> function) {

        readOnlyList.add(new FunctionAndCompare<>(function,
                new CompareWithEquals<>(),
                addReadOnlyPosition,
                true,
                atomicTestId,
                automaticTestMethodId,
                READ));
        addReadOnlyPosition++;
        return this;
    }

    public AtomicTestBuilder<CLASS_UNDER_TEST> addWrite(Consumer<CLASS_UNDER_TEST> consumer) {

        writeList.add(new ConsumerWrapper<>(consumer,addWritePosition, atomicTestId, automaticTestMethodId));
        automaticTestMethodId++;
        addWritePosition++;
        return this;
    }

    public <READ_VALUE> AtomicTestBuilder<CLASS_UNDER_TEST> addUpdate(Function<CLASS_UNDER_TEST,READ_VALUE> function) {

        writeList.add(new FunctionAndCompare<>(function,
                new CompareWithEquals<>(),
                addUpdatePosition,
                false,
                atomicTestId,
                automaticTestMethodId,
                WRITE));
        addUpdatePosition++;
        return this;
    }

    public void runTests() {
        new AtomicTestImpl<>(createClassUnderTest,writeList,readOnlyList,atomicTestId).runTests();
    }

}
