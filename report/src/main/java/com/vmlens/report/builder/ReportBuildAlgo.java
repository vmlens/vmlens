package com.vmlens.report.builder;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.element.RunElement;
import com.vmlens.report.element.StacktraceElement;
import com.vmlens.report.element.StacktraceLeaf;
import com.vmlens.report.uielement.*;

import java.util.LinkedList;
import java.util.List;

public class ReportBuildAlgo {

    private final List<LoopAndRun> loopAndRuns;
    private final List<StacktraceLeaf> stacktraceLeafs;
    private final DescriptionContext descriptionContext;


    public ReportBuildAlgo(List<LoopAndRun> loopAndRuns,
                           List<StacktraceLeaf> stacktraceLeafs,
                           DescriptionContext descriptionContext) {
        this.loopAndRuns = loopAndRuns;
        this.stacktraceLeafs = stacktraceLeafs;
        this.descriptionContext = descriptionContext;
    }

    public UILoopsAndStacktraceLeafs build() {


        List<UIStacktraceLeaf> leafNodes = new LinkedList<>();
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


                UIRunElement uiRunElement = new UIRunElement(runElement.operationTextFactory().create(descriptionContext),
                        firstStacktraceMethodName, descriptionContext.threadName(runElement.loopRunAndThreadIndex()));

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
