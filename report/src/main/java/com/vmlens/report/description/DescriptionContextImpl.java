package com.vmlens.report.description;


import com.vmlens.report.container.ContainerForField;
import com.vmlens.report.container.ContainerForMethod;
import com.vmlens.report.container.ContainerForTestLoop;
import com.vmlens.report.container.ContainerForThread;
import com.vmlens.report.input.LoopRunAndThreadIndex;

import java.util.Map;

public class DescriptionContextImpl implements DescriptionContext {

    private final ContainerMapAdapter<LoopRunAndThreadIndex, ContainerForThread> threadNames;
    private final ContainerMapAdapter<Integer, ContainerForMethod> methodNames;
    private final ContainerMapAdapter<Integer, ContainerForField> fieldNames;
    private final ContainerMapAdapter<Integer, ContainerForTestLoop> loopNames;

    public DescriptionContextImpl(Map<LoopRunAndThreadIndex, ContainerForThread> indexToThreadDescription,
                                  Map<Integer, ContainerForMethod> idToMethodContainer,
                                  Map<Integer, ContainerForField> idToFieldContainer,
                                  Map<Integer, ContainerForTestLoop> idToTestLoopDescription) {
        this.threadNames = new ContainerMapAdapter<>(indexToThreadDescription);
        this.methodNames = new ContainerMapAdapter<>(idToMethodContainer);
        this.fieldNames = new ContainerMapAdapter<>(idToFieldContainer);
        this.loopNames = new ContainerMapAdapter<>(idToTestLoopDescription);
    }

    @Override
    public String threadName(LoopRunAndThreadIndex key) {
        return threadNames.getName(key);
    }

    public String methodName(Integer key) {
        return methodNames.getName(key);
    }

    public String fieldName(Integer key) {
        return fieldNames.getName(key);
    }

    public String loopName(Integer key) {
        return loopNames.getName(key);
    }

}
