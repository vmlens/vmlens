package com.vmlens.report.createreport;

import com.vmlens.report.output.UILoopsAndStacktraceLeafs;

import java.io.IOException;

public interface CreateReport {

    void createReport(UILoopsAndStacktraceLeafs container)
            throws IOException;

}
