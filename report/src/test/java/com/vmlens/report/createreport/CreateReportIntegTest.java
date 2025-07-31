package com.vmlens.report.createreport;

import com.vmlens.report.uielement.*;
import org.apache.commons.io.FileUtils;
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
        UIStacktraceElement first = new UIStacktraceElement("org.apache.commons.text.StringEscapeUtils.escapeHtml3");
        stacktraceElements.add(first);

        UIStacktraceLeaf root = new UIStacktraceLeaf(stacktraceElements);

        List<UIStacktraceLeaf> rootNodes = new LinkedList<>();
        rootNodes.add(root);

        List<UILoopAndRunElementWithStacktraceLeafs> uiLoopAndRunElementsList = new LinkedList<>();

        UITestLoop uiLoop = new UITestLoop("name", 0, "resultText", "style=\"color: red;\"");
        List<UIRunElementWithStacktraceLeaf> uiRunElementWithStacktraceRoots = new
                LinkedList<>();

        UIRunElement uiRunElement = new UIRunElement("<b>interleaveoperation</b> for field", "method", "4" , false);
        uiRunElementWithStacktraceRoots.add(new UIRunElementWithStacktraceLeaf(uiRunElement, root));

        UILoopAndRunElementWithStacktraceLeafs uiLoopAndRunElementWithStacktraceRoots =
                new UILoopAndRunElementWithStacktraceLeafs(uiLoop, uiRunElementWithStacktraceRoots);

        uiLoopAndRunElementsList.add(uiLoopAndRunElementWithStacktraceRoots);
        createReport.createReport(new UILoopsAndStacktraceLeafs(rootNodes, uiLoopAndRunElementsList));

        FileUtils.deleteDirectory(outputDir.toFile());
    }

}
