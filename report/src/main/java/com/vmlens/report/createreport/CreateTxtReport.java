package com.vmlens.report.createreport;

import com.vmlens.report.uielement.UILoopAndRunElementWithStacktraceLeafs;
import com.vmlens.report.uielement.UILoopsAndStacktraceLeafs;
import com.vmlens.report.uielement.UIRunElement;
import com.vmlens.report.uielement.UIRunElementWithStacktraceLeaf;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class CreateTxtReport implements CreateReport {

    private static final String TXT_FILE = ".txt";
    private final Path reportDir;

    public CreateTxtReport(Path reportDir) {
        this.reportDir = reportDir;
    }

    @Override
    public void createReport(UILoopsAndStacktraceLeafs container) throws IOException {
        CreateOneReport createOneReport = new CreateOneReport("runAsTxt");
        for (UILoopAndRunElementWithStacktraceLeafs loop : container.uiLoopAndRunElementsList()) {
            String fileName = loop.uiTestLoop().name().replaceAll("\\s", "_") + TXT_FILE;
            List<UIRunElement> uiRunElements = new LinkedList<>();
            for (UIRunElementWithStacktraceLeaf element : loop.uiRunElementWithStacktraceRoots()) {
                uiRunElements.add(element.createRunElement());
            }
            OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(reportDir.resolve(fileName)));
            createOneReport.createUIRun(uiRunElements, "" , writer);
            writer.close();
        }
    }
}
