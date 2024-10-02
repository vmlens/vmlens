package com.vmlens.report.createreport;

import com.vmlens.report.uielement.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class CreateReport {

    private static final String HTML_FILE = ".html";
    private final Path reportDir;

    public CreateReport(Path reportDir) {
        this.reportDir = reportDir;
    }

    public void createReport(UILoopAndStacktraceRoots container)
            throws IOException {
        new CopyStaticFiles(reportDir).copy();

        createStacktraceReport(container.rootNodes());
        createRunReport(container.uiLoopAndRunElementsList());
        createIndex(container.uiLoopAndRunElementsList());
    }

    private void createIndex(List<UILoopAndRunElementWithStacktraceRoots> uiLoopAndRunElementWithStacktraceRoots) throws IOException {
        int index = 0;
        List<UITestLoop> uiTestLoops = new LinkedList<>();
        for (UILoopAndRunElementWithStacktraceRoots loop : uiLoopAndRunElementWithStacktraceRoots) {
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

    private void createRunReport(List<UILoopAndRunElementWithStacktraceRoots> uiLoopAndRunElementWithStacktraceRoots)
            throws IOException {

        CreateOneReport createOneReport = new CreateOneReport("run");
        int index = 0;
        for (UILoopAndRunElementWithStacktraceRoots loop : uiLoopAndRunElementWithStacktraceRoots) {
            String fileName = "run" + index + HTML_FILE;
            loop.uiTestLoop().setLink(fileName);
            index++;

            List<UIRunElement> uiRunElements = new LinkedList<>();
            for (UIRunElementWithStacktraceRoot element : loop.uiRunElementWithStacktraceRoots()) {
                UIRunElement runElement = element.runElement();
                runElement.setLink(element.stacktraceRoot().reportLink());
                uiRunElements.add(runElement);
            }

            OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
            createOneReport.createUIRun(uiRunElements, writer);
            writer.close();
        }


    }

    private void createStacktraceReport(List<UIStacktraceRoot> rootNodes) throws IOException {
        CreateOneReport createOneReport = new CreateOneReport("stacktrace");
        int index = 0;
        for (UIStacktraceRoot root : rootNodes) {
            String fileName = "stack" + index + HTML_FILE;
            root.setReportLink(fileName);
            OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
            createOneReport.createUIStacktraceElement(root.stacktraceElements(), writer);
            writer.close();
            index++;
        }
    }

}
