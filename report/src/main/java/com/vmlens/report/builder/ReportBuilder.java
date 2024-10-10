package com.vmlens.report.builder;

import com.anarsoft.trace.agent.description.*;
import com.vmlens.report.element.*;
import com.vmlens.report.uielement.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ReportBuilder implements NeedsDescriptionCallback, ThreadOrLoopDescriptionVisitor {

    private static final String ID_NOT_FOUND = "missing %s";

    private final Map<Integer, FieldContainer> idToFieldContainer = new HashMap<>();
    private final Map<Integer, MethodContainer> idToMethodContainer = new HashMap<>();
    private final Map<LoopRunAndThreadIndex, ThreadContainer> indexToThreadDescription = new HashMap<>();
    private final Map<Integer, TestLoopContainer> idToTestLoopDescription = new HashMap<>();

    private final List<LoopAndRun> loopAndRuns = new LinkedList<>();
    private final List<StacktraceLeaf> stacktraceLeafs = new LinkedList<>();

    public void addClassDescription(ClassDescription classDescription) {
        for (FieldInClassDescription fieldInClassDescription : classDescription.serializedFieldDescriptionArray()) {
            FieldContainer fieldContainer = idToFieldContainer.get(fieldInClassDescription.id());
            if (fieldContainer != null) {
                fieldContainer.setClassDescription(classDescription);
                fieldContainer.setFieldInClassDescription(fieldInClassDescription);
            }
        }
        for (MethodDescription methodDescription : classDescription.methodArray()) {
            for (FieldAccessDescription fieldAccessDescription : methodDescription.fieldArray()) {
                FieldContainer fieldContainer = idToFieldContainer.get(fieldAccessDescription.id());
                if (fieldContainer != null) {
                    fieldContainer.setFieldAccessDescription(fieldAccessDescription);
                }
            }

            MethodContainer methodContainer = idToMethodContainer.get(methodDescription.id());
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
        indexToThreadDescription.put(loopRunAndThreadIndex, new ThreadContainer(threadDescription));

    }

    @Override
    public void visit(TestLoopDescription whileLoopDescription) {
        idToTestLoopDescription.put(whileLoopDescription.loopId(), new TestLoopContainer(whileLoopDescription));
    }

    public void addLoopAndRun(TestLoop testLoop, List<RunElement> run) {
        for (RunElement element : run) {
            element.operationTextFactory().addToNeedsDescription(this);
        }
        loopAndRuns.add(new LoopAndRun(testLoop, run));
    }

    public void addStacktraceLeaf(StacktraceLeaf stacktraceLeaf) {
        stacktraceLeafs.add(stacktraceLeaf);

        for (StacktraceElement stacktraceElement : stacktraceLeaf.stacktraceElements()) {
            idToMethodContainer.put(stacktraceElement.methodId(), new MethodContainer());
        }
    }

    public UILoopsAndStacktraceLeafs build() {
        List<UIStacktraceLeaf> leafNodes = new LinkedList<>();
        List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementsList = new LinkedList<>();

        for (LoopAndRun loopAndRun : loopAndRuns) {
            UITestLoop uiTestLoop = new UITestLoop(getLoopNameByLoopId(loopAndRun.loop().loopId()),
                    loopAndRun.loop().count(),
                    loopAndRun.loop().testResult().text(), loopAndRun.loop().testResult().style());


            loopAndRun.runElements().sort(new RunElementByRunPositionComparator());
            
            List<UIRunElementWithStacktraceLeaf> uiRunElementWithStacktraceLeafs = new LinkedList<>();

            for (RunElement runElement : loopAndRun.runElements()) {
                String firstStacktraceMethodName = null;

                List<UIStacktraceElement> stacktraceElements = new LinkedList<>();

                for (StacktraceElement stacktraceElement : runElement.stacktraceLeaf().stacktraceElements()) {
                    String methodName = getMethodNameByMethodId(stacktraceElement.methodId());
                    if (firstStacktraceMethodName == null) {
                        firstStacktraceMethodName = methodName;
                    }
                    stacktraceElements.add(new UIStacktraceElement(methodName));
                }

                UIRunElement uiRunElement = new UIRunElement(runElement.operationTextFactory().create(),
                        firstStacktraceMethodName, getThreadIdByThreadIndex(runElement.loopRunAndThreadIndex()));

                UIStacktraceLeaf uiStacktraceLeaf = new UIStacktraceLeaf(stacktraceElements);
                UIRunElementWithStacktraceLeaf uiRunElementWithStacktraceLeaf =
                        new UIRunElementWithStacktraceLeaf(uiRunElement, uiStacktraceLeaf);
                uiRunElementWithStacktraceLeafs.add(uiRunElementWithStacktraceLeaf);
            }

            uiLoopAndRunElementsList.add(
                    new UILoopAndRunElementWithStacktraceLeafs(uiTestLoop, uiRunElementWithStacktraceLeafs));
        }
        return new UILoopsAndStacktraceLeafs(leafNodes, uiLoopAndRunElementsList);
    }

    @Override
    public void needsField(int fieldId) {
        idToFieldContainer.put(fieldId, new FieldContainer());
    }

    private String getMethodNameByMethodId(int methodId) {
        return getName(methodId, idToMethodContainer);
    }

    private String getThreadIdByThreadIndex(LoopRunAndThreadIndex loopRunAndThreadIndex) {
        return getName(loopRunAndThreadIndex, indexToThreadDescription);
    }

    private String getLoopNameByLoopId(int loopId) {
        return getName(loopId, idToTestLoopDescription);
    }

    private String getName(Object index, Map<?, ? extends Container> idToContainer) {
        String result = null;
        Container container = idToMethodContainer.get(index);
        if (container != null) {
            result = container.getName();
        }
        if (result != null) {
            return result;
        }
        return String.format(ID_NOT_FOUND, index);
    }
}
