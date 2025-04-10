package com.vmlens.report.builder;

import com.anarsoft.trace.agent.description.*;
import com.vmlens.report.container.ContainerForField;
import com.vmlens.report.container.ContainerForMethod;
import com.vmlens.report.container.ContainerForTestLoop;
import com.vmlens.report.container.ContainerForThread;
import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.DescriptionContextImpl;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.element.*;
import com.vmlens.report.uielement.UILoopsAndStacktraceLeafs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ReportBuilder implements NeedsDescriptionCallback, ThreadOrLoopDescriptionVisitor {

    private final Map<Integer, ContainerForField> idToFieldContainer = new HashMap<>();
    private final Map<Integer, ContainerForMethod> idToMethodContainer = new HashMap<>();
    private final Map<LoopRunAndThreadIndex, ContainerForThread> indexToThreadDescription = new HashMap<>();
    private final Map<Integer, ContainerForTestLoop> idToTestLoopDescription = new HashMap<>();

    private final List<LoopAndRun> loopAndRuns = new LinkedList<>();
    private final List<StacktraceLeaf> stacktraceLeafs = new LinkedList<>();

    public void addClassDescription(ClassDescription classDescription) {
        for (FieldInClassDescription fieldInClassDescription : classDescription.serializedFieldDescriptionArray()) {
            ContainerForField fieldContainer = idToFieldContainer.get(fieldInClassDescription.id());
            if (fieldContainer != null) {
                fieldContainer.setClassDescription(classDescription);
                fieldContainer.setFieldInClassDescription(fieldInClassDescription);
            }
        }
        for (MethodDescription methodDescription : classDescription.methodArray()) {


            ContainerForMethod methodContainer = idToMethodContainer.get(methodDescription.id());
            if (methodContainer != null) {
                methodContainer.setMethodDescription(methodDescription);
                methodContainer.setClassDescription(classDescription);
            }
        }
    }

    public void addThreadOrLoopDescription(ThreadOrLoopDescription threadOrLoopDescription) {
        threadOrLoopDescription.accept(this);
    }


    @Override
    public void visit(ThreadDescription threadDescription) {
        LoopRunAndThreadIndex loopRunAndThreadIndex = new LoopRunAndThreadIndex(threadDescription.loopId(),
                threadDescription.runId(),
                threadDescription.threadIndex());
        indexToThreadDescription.put(loopRunAndThreadIndex, new ContainerForThread(threadDescription));

    }

    @Override
    public void visit(TestLoopDescription whileLoopDescription) {
        idToTestLoopDescription.put(whileLoopDescription.loopId(), new ContainerForTestLoop(whileLoopDescription));
    }

    public void addLoopAndRun(TestLoop testLoop, List<RunElement> run) {
        for (RunElement element : run) {
            idToMethodContainer.put(element.inMethodId(), new ContainerForMethod());
            element.operationTextFactory().addToNeedsDescription(this);
        }
        loopAndRuns.add(new LoopAndRun(testLoop, run));
    }

    public void addStacktraceLeaf(StacktraceLeaf stacktraceLeaf) {
        stacktraceLeafs.add(stacktraceLeaf);

        for (StacktraceElement stacktraceElement : stacktraceLeaf.stacktraceElements()) {
            idToMethodContainer.put(stacktraceElement.methodId(), new ContainerForMethod());
        }
    }

    public UILoopsAndStacktraceLeafs build() {
        DescriptionContext operationTextFactoryContext =
                new DescriptionContextImpl(indexToThreadDescription,
                        idToMethodContainer,
                        idToFieldContainer,
                        idToTestLoopDescription);


        return new ReportBuildAlgo(loopAndRuns, operationTextFactoryContext).build();
    }

    @Override
    public void needsField(int fieldId) {
        idToFieldContainer.put(fieldId, new ContainerForField());
    }

    @Override
    public void needsMethod(int methodId) {
        idToMethodContainer.put(methodId,new ContainerForMethod());
    }
}
