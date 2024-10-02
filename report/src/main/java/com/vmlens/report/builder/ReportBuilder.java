package com.vmlens.report.builder;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.description.TestLoopDescription;
import com.anarsoft.trace.agent.description.ThreadDescription;
import com.vmlens.report.element.*;
import com.vmlens.report.uielement.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class ReportBuilder {

    private final Map<Integer, FieldContainer> idToFieldContainer = new HashMap<>();
    private final Map<Integer, MethodContainer> idToMethodContainer = new HashMap<>();
    private final Map<LoopRunAndThreadIndex, ThreadDescription> indexToThreadDescription = new HashMap<>();
    private final Map<Integer, TestLoopDescription> idToTestLoopDescription = new HashMap<>();

    private final List<LoopAndRun> loopAndRuns = new LinkedList<>();

    public void addClassDescription(ClassDescription classDescription) {

    }

    public void addLoopAndRun(TestLoop testLoop, List<RunElement> run) {
        loopAndRuns.add(new LoopAndRun(testLoop, run));
    }


    public UILoopsAndStacktraceLeafs build() {
        List<UIStacktraceLeaf> leafNodes = new LinkedList<>();
        List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementsList = new LinkedList<>();

        for (LoopAndRun loopAndRun : loopAndRuns) {
            UITestLoop uiTestLoop = new UITestLoop("name",
                    loopAndRun.loop().count(),
                    loopAndRun.loop().resultText(), loopAndRun.loop().resultStyle());

            List<UIRunElementWithStacktraceLeaf> uiRunElementWithStacktraceLeafs = new LinkedList<>();

            // Fixme sort  loopAndRun.runElements() by runPosition

            for (RunElement runElement : loopAndRun.runElements()) {
                // (String operation, String method, String threadId)
                UIRunElement uiRunElement = new UIRunElement(runElement.operationTextFactory().create(),
                        "missing", "unknown");

                List<UIStacktraceElement> stacktraceElements = new LinkedList<>();
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
}
