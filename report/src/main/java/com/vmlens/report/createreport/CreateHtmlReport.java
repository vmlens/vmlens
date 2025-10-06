package com.vmlens.report.createreport;

import com.vmlens.report.uielement.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CreateHtmlReport implements CreateReport{

    private static final String HTML_FILE = ".html";
    private final Path reportDir;
    private final boolean showAllRuns;

    public CreateHtmlReport(Path reportDir,
                            boolean showAllRuns) {
        this.reportDir = reportDir;
        this.showAllRuns = showAllRuns;
    }

    public void createReport(UILoopsAndStacktraceLeafs container)
            throws IOException {
        new CopyStaticFiles(reportDir).copy();
        createStacktraceReport(container.rootNodes());
        // index must be before createRunReport
        // since here the elements get sorted and rhe index get set
        createIndex(container.uiLoopAndRunElementsList());
        createRunReport(container.uiLoopAndRunElementsList());

    }

    private void createIndex(List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementWithStacktraceRoots) throws IOException {
        uiLoopAndRunElementWithStacktraceRoots.sort(new UILoopAndRunElementWithStacktraceLeafsComparator());
        List<UITestLoopOrWarning> uiTestLoops = new LinkedList<>();
        int index = 0;
        for (UILoopAndRunElementWithStacktraceLeafs loop : uiLoopAndRunElementWithStacktraceRoots) {
            loop.uiTestLoop().setIndex(index);
            index++;
            String fileName = "run" + loop.uiTestLoop().index() + HTML_FILE;
            loop.uiTestLoop().setLink(fileName);

            uiTestLoops.add(loop.uiTestLoop());
            uiTestLoops.addAll(loop.uiWarnings());
        }
        String fileName = "index" + HTML_FILE;
        CreateOneReport createOneReport = new CreateOneReport("index");
        OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
        createOneReport.createUITestLoop(uiTestLoops, writer);
        writer.close();
    }

    private void createRunReport(List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementWithStacktraceRoots)
            throws IOException {
        CreateOneReport completeReport = new CreateOneReport("run");
        CreateOneReport summaryReport = new CreateOneReport("summary");

        for (UILoopAndRunElementWithStacktraceLeafs loop : uiLoopAndRunElementWithStacktraceRoots) {
            String fileName = "run" + loop.uiTestLoop().index() + HTML_FILE;
            if(! showAllRuns && loop.uiRunElementWithStacktraceRoots().size() > 100) {
                Map<UIRunElement,Integer> runElementToCount = new HashMap<>();
                for (UIRunElementWithStacktraceLeaf element : loop.uiRunElementWithStacktraceRoots()) {
                    UIRunElement runElement = element.createRunElement();
                    Integer count = runElementToCount.get(runElement);
                    if(count == null) {
                        runElementToCount.put(runElement,1);
                    } else {
                        runElementToCount.put(runElement,count + 1);
                    }
                }
                List<UISummaryElement> uiSummaryElements = new LinkedList<>();
                for(Map.Entry<UIRunElement,Integer> entry : runElementToCount.entrySet())  {
                    uiSummaryElements.add(entry.getKey().toSummary(entry.getValue()));
                }
                uiSummaryElements.sort(new UISummaryElementComparator());
                OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
                summaryReport.createUISummary(uiSummaryElements, loop.uiTestLoop().name(), writer);
                writer.close();

            } else {
                List<UIRunElement> uiRunElements = new LinkedList<>();
                for (UIRunElementWithStacktraceLeaf element : loop.uiRunElementWithStacktraceRoots()) {
                    uiRunElements.add(element.createRunElement());
                }
                OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
                completeReport.createUIRun(uiRunElements, loop.uiTestLoop().name(), writer);
                writer.close();
            }
        }
    }

    private void createStacktraceReport(Collection<UIStacktraceLeaf> rootNodes) throws IOException {
        CreateOneReport createOneReport = new CreateOneReport("stacktrace");
        int index = 0;
        for (UIStacktraceLeaf root : rootNodes) {
            String fileName = "stack" + index + HTML_FILE;
            root.setReportLink(fileName);
            OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
            createOneReport.createUIStacktraceElement(root.stacktraceElements(), writer);
            writer.close();
            index++;
        }
    }
}
