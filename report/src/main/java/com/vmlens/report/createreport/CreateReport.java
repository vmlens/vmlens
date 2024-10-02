package com.vmlens.report.createreport;

import com.vmlens.report.uielement.UILoopAndRunElementWithStacktraceRoots;
import com.vmlens.report.uielement.UIStacktraceRoot;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CreateReport {

    private static final String HTML_FILE = ".html";
    private final Path reportDir;

    public CreateReport(Path reportDir) {
        this.reportDir = reportDir;
    }

    public void createReport(List<UIStacktraceRoot> rootNodes, List<UILoopAndRunElementWithStacktraceRoots> uiLoopAndRunElementsList)
            throws IOException {
        new CopyStaticFiles(reportDir).copy();


        CreateOneReport createOneReport = new CreateOneReport("stacktrace");
        int index = 0;

        for (UIStacktraceRoot root : rootNodes) {
            String fileName = "stack" + index + HTML_FILE;
            OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName).toFile().toPath()));
            createOneReport.createUIStacktraceElement(root.stacktraceElements(), writer);
            writer.close();
        }

    }
}
