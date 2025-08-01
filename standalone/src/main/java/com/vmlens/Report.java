package com.vmlens;

import com.anarsoft.race.detection.main.ProcessEvents;
import com.anarsoft.race.detection.process.run.ProcessRunContextBuilder;
import picocli.CommandLine;

import java.io.File;

import static com.vmlens.setup.SetupAgent.eventDir;

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
                new ProcessRunContextBuilder().build()).process();

        System.out.println("Report written to: " + reportDirectory.getAbsolutePath());
    }
}
