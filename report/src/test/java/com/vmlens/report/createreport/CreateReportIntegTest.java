package com.vmlens.report.createreport;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateReportIntegTest {

    @Test
    public void createReport() throws IOException {
        Path outputDir = Files.createTempDirectory("testDir");
        System.out.println("writing report to " + outputDir);

        CreateReport createReport = new CreateReport(outputDir);
        createReport.createReport(null);
    }

}
