package com.vmlens.report.createreport;

import com.vmlens.report.dominatortree.UIDominatorTreeElement;
import com.vmlens.report.dominatortree.UIReverseCallTree;
import com.vmlens.report.overview.UITestLoopAndWarning;
import com.vmlens.report.overview.UITestLoopAndWarningComparator;
import com.vmlens.report.overview.UITestLoopOrWarning;
import com.vmlens.report.stacktrace.UIStacktraceElement;
import com.vmlens.report.summary.UISummaryElement;
import com.vmlens.report.trace.UIRunElement;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class CreateHtmlReport {

    private static final String HTML_FILE = ".html";
    private final Path reportDir;
    private int stacktraceIndex = 0;

    public CreateHtmlReport(Path reportDir) {
        this.reportDir = reportDir;
    }

    public void copyStaticFiled()
            throws IOException {
        new CopyStaticFiles(reportDir).copy();
    }

    public void createOverview(List<UITestLoopAndWarning> uiTestLoopAndWarnings) throws IOException {
        uiTestLoopAndWarnings.sort(new UITestLoopAndWarningComparator());
        List<UITestLoopOrWarning> uiTestLoops = new LinkedList<>();
        int index = 0;
        for (UITestLoopAndWarning loop : uiTestLoopAndWarnings) {
            loop.uiTestLoop().setIndex(index);
            index++;
            String fileName = "run" + loop.uiTestLoop().index() + HTML_FILE;
            String fileNameDominatorTree = "state" + loop.uiTestLoop().index() + HTML_FILE;
            loop.setLinks(fileName,fileNameDominatorTree, "state" + loop.uiTestLoop().index() );
            uiTestLoops.add(loop.uiTestLoop());
            uiTestLoops.addAll(loop.uiWarnings());
        }
        String fileName = "index" + HTML_FILE;
        CreateOneReport createOneReport = new CreateOneReport("index");
        OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
        createOneReport.createUITestLoop(uiTestLoops, writer);
        writer.close();
    }

    public void createRunReport(List<UIRunElement> uiRunElements, String name, String fileName)
            throws IOException {
        CreateOneReport completeReport = new CreateOneReport("run");
        OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
        completeReport.createUIRun(uiRunElements,name, writer);
        writer.close();
    }

    public void createSummaryReport(List<UISummaryElement> uiRunElements, String name, String fileName)
            throws IOException {
        CreateOneReport summaryReport = new CreateOneReport("summary");
        OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
        summaryReport.createUISummary(uiRunElements,name, writer);
        writer.close();
    }
    
    public String createStacktraceReport(List<UIStacktraceElement> stacktraceElements) throws IOException {
        CreateOneReport createOneReport = new CreateOneReport("stacktrace");
        String fileName = "stack" + stacktraceIndex + HTML_FILE;
        OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
        createOneReport.createUIStacktraceElement(stacktraceElements, writer);
        writer.close();
        stacktraceIndex++;
        return  fileName;
    }

    public void createDominatorTreeReport(List<UIDominatorTreeElement> uiRunElements, String name, String fileName)
            throws IOException {
        CreateOneReport summaryReport = new CreateOneReport("dominatorTree");
        OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
        summaryReport.createDominatorTree(uiRunElements,name, writer);
        writer.close();
    }

    public void createReverseCallTree(List<UIReverseCallTree> uiRunElements, String name, String fileName)
            throws IOException {
        CreateOneReport summaryReport = new CreateOneReport("reverseCallTree");
        OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
        summaryReport.createReverseCallTree(uiRunElements,name, writer);
        writer.close();
    }
    
}
