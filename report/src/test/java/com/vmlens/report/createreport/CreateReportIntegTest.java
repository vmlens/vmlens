package com.vmlens.report.createreport;

import com.vmlens.report.uielement.UILoopAndRunElementWithStacktraceRoots;
import com.vmlens.report.uielement.UIStacktraceElement;
import com.vmlens.report.uielement.UIStacktraceRoot;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class CreateReportIntegTest {

    @Test
    public void createReport() throws IOException {
        Path outputDir = Files.createTempDirectory("testDir");
        System.out.println("writing report to " + outputDir);

        CreateReport createReport = new CreateReport(outputDir);

        List<UIStacktraceElement> stacktraceElements = new LinkedList<>();
        UIStacktraceElement first = new UIStacktraceElement();
        first.setText("org.apache.commons.text.StringEscapeUtils.escapeHtml3");
        stacktraceElements.add(first);

        UIStacktraceRoot root = new UIStacktraceRoot(stacktraceElements);

        List<UIStacktraceRoot> rootNodes = new LinkedList<>();
        rootNodes.add(root);

        List<UILoopAndRunElementWithStacktraceRoots> uiLoopAndRunElementsList = new LinkedList<>();

        createReport.createReport(rootNodes, uiLoopAndRunElementsList);
    }

}
