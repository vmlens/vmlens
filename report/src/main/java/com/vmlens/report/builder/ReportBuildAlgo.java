package com.vmlens.report.builder;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.element.RunElement;
import com.vmlens.report.element.StacktraceElement;
import com.vmlens.report.uielement.*;

import java.util.*;

public class ReportBuildAlgo {

    private static final UIStacktraceLeaf EMPTY_STACKTRACE_LEAF = new UIStacktraceLeaf(Collections.emptyList());

    private final List<LoopAndRun> loopAndRuns;
    private final DescriptionContext descriptionContext;


    public ReportBuildAlgo(List<LoopAndRun> loopAndRuns,
                           DescriptionContext descriptionContext) {
        this.loopAndRuns = loopAndRuns;
        this.descriptionContext = descriptionContext;
    }

    public UILoopsAndStacktraceLeafs build() {


        Map<List<UIStacktraceElement>, UIStacktraceLeaf> leafNodes = new HashMap<>();
        List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementsList = new LinkedList<>();

        for (LoopAndRun loopAndRun : loopAndRuns) {
            UITestLoop uiTestLoop = new UITestLoop(descriptionContext.loopName(loopAndRun.loop().loopId()),
                    loopAndRun.loop().count(),
                    loopAndRun.loop().testResult().text(), loopAndRun.loop().testResult().style());


            loopAndRun.runElements().sort(new RunElementByRunPositionComparator());

            List<UIRunElementWithStacktraceLeaf> uiRunElementWithStacktraceLeafs = new LinkedList<>();

            for (RunElement runElement : loopAndRun.runElements()) {
                String firstStacktraceMethodName = null;

                List<UIStacktraceElement> stacktraceElements = new LinkedList<>();
                for (StacktraceElement stacktraceElement : runElement.stacktraceLeaf().stacktraceElements()) {
                    String methodName = descriptionContext.methodName(stacktraceElement.methodId());
                    if (firstStacktraceMethodName == null) {
                        firstStacktraceMethodName = methodName;
                    }
                    stacktraceElements.add(new UIStacktraceElement(methodName));
                }

                if (firstStacktraceMethodName == null) {
                    // Happens when no stacktrace exist
                    // for example for the main test thread
                    firstStacktraceMethodName = descriptionContext.methodName(runElement.inMethodId());
                }

                UIRunElement uiRunElement = new UIRunElement(runElement.operationTextFactory().asString(descriptionContext),
                        firstStacktraceMethodName, descriptionContext.threadName(runElement.loopRunAndThreadIndex()));

                UIStacktraceLeaf uiStacktraceLeaf;
                if (stacktraceElements.size() > 0) {
                    uiStacktraceLeaf = leafNodes.get(stacktraceElements);
                    if (uiStacktraceLeaf == null) {
                        uiStacktraceLeaf = new UIStacktraceLeaf(stacktraceElements);
                        leafNodes.put(stacktraceElements, uiStacktraceLeaf);
                    }
                } else {
                    uiStacktraceLeaf = EMPTY_STACKTRACE_LEAF;
                }

                UIRunElementWithStacktraceLeaf uiRunElementWithStacktraceLeaf =
                        new UIRunElementWithStacktraceLeaf(uiRunElement, uiStacktraceLeaf);
                uiRunElementWithStacktraceLeafs.add(uiRunElementWithStacktraceLeaf);
            }

            uiLoopAndRunElementsList.add(
                    new UILoopAndRunElementWithStacktraceLeafs(uiTestLoop, uiRunElementWithStacktraceLeafs));
        }
        return new UILoopsAndStacktraceLeafs(leafNodes.values(), uiLoopAndRunElementsList);
    }
}
