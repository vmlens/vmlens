package com.vmlens.report.createreport;

import com.vmlens.report.domain.UIStacktraceRootAndLoopAndRunElements;

import java.io.IOException;
import java.nio.file.Path;

public class CreateReport {

    private final Path reportDir;

    public CreateReport(Path reportDir) {
        this.reportDir = reportDir;
    }

    public void createReport(UIStacktraceRootAndLoopAndRunElements uiCollection) throws IOException {
        new CopyStaticFiles(reportDir).copy();


    }
}
