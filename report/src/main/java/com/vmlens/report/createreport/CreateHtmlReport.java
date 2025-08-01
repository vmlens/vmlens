package com.vmlens.report.createreport;

import com.vmlens.report.uielement.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CreateHtmlReport implements CreateReport{

    private static final String HTML_FILE = ".html";
    private final Path reportDir;

    public CreateHtmlReport(Path reportDir) {
        this.reportDir = reportDir;
    }

    public void createReport(UILoopsAndStacktraceLeafs container)
            throws IOException {
        new CopyStaticFiles(reportDir).copy();

        createStacktraceReport(container.rootNodes());
        createRunReport(container.uiLoopAndRunElementsList());
        createIndex(container.uiLoopAndRunElementsList());
    }

    private void createIndex(List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementWithStacktraceRoots) throws IOException {
        int index = 0;
        List<UITestLoop> uiTestLoops = new LinkedList<>();
        for (UILoopAndRunElementWithStacktraceLeafs loop : uiLoopAndRunElementWithStacktraceRoots) {
            loop.uiTestLoop().setIndex(index);
            uiTestLoops.add(loop.uiTestLoop());
            index++;
        }
        String fileName = "index" + HTML_FILE;
        CreateOneReport createOneReport = new CreateOneReport("index");
        OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
        createOneReport.createUITestLoop(uiTestLoops, writer);
        writer.close();
    }

    private void createRunReport(List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementWithStacktraceRoots)
            throws IOException {

        CreateOneReport createOneReport = new CreateOneReport("run");
        int index = 0;
        for (UILoopAndRunElementWithStacktraceLeafs loop : uiLoopAndRunElementWithStacktraceRoots) {
            String fileName = "run" + index + HTML_FILE;
            loop.uiTestLoop().setLink(fileName);
            index++;

            List<UIRunElement> uiRunElements = new LinkedList<>();
            for (UIRunElementWithStacktraceLeaf element : loop.uiRunElementWithStacktraceRoots()) {
                uiRunElements.add(element.createRunElement());
            }
            OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
            createOneReport.createUIRun(uiRunElements, writer);
            writer.close();
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
