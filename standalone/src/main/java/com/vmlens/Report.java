package com.vmlens;

import com.anarsoft.race.detection.main.ProcessEvents;
import com.vmlens.report.assertion.OnDescriptionAndLeftBeforeRightNoOp;
import com.vmlens.report.assertion.OnEventNoOp;
import picocli.CommandLine;

import java.io.File;

import static com.vmlens.setup.Setup.*;

@CommandLine.Command(name = "report", description = "Creates the report.")
public class Report implements Runnable {

    @CommandLine.ParentCommand
    private Standalone parent;

    @Override
    public void run() {
        File eventDirectory = new File(eventDir(parent.agentDirectory)  );
        File reportDirectory = parent.reportDirectory;

        new ProcessEvents(eventDirectory.toPath(),
                reportDirectory.toPath(),
                new OnDescriptionAndLeftBeforeRightNoOp(), new OnEventNoOp()).process();

        System.out.println("Report written to: " + reportDirectory.getAbsolutePath());
    }
}
