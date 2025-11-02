package com.vmlens.gradle;

import com.anarsoft.race.detection.main.ProcessEvents;
import com.anarsoft.race.detection.process.run.ProcessRunContextBuilder;
import com.vmlens.report.ResultForVerify;
import com.vmlens.setup.EventDirectoryAndArgLine;
import com.vmlens.setup.SetupAgent;

import java.io.File;
import java.nio.file.Path;

import static com.vmlens.setup.Messages.dataRaces;

public class VMLens {

    private final ProcessRunContextBuilder processRunContextBuilder = new ProcessRunContextBuilder();

    public VMLens withShowAllRuns() {
        processRunContextBuilder.withShowAllRuns();
        return this;
    }

    public VMLens withShowAllMemoryAccess() {
        processRunContextBuilder.withShowAllMemoryAccess();
        return this;
    }

    public VMLens withTxtFormat() {
        processRunContextBuilder.withTxtFormat();
        return this;
    }

    public void process(File buildDir) {
        File agentDirectory = new File(buildDir, SetupAgent.AGENT_DIRECTORY);
        File reportDirectory = new File(buildDir, SetupAgent.REPORT_DIRECTORY);

        Path eventPath = new File(SetupAgent.eventDir(agentDirectory)).toPath();
        Path reportPath = reportDirectory.toPath();

        ProcessEvents processEvents =
                new ProcessEvents(
                        eventPath,
                        reportPath,
                        processRunContextBuilder.build()
                );

        if(! processEvents.hasThreadAndLoopDescription()) {
            return;
        }

        ResultForVerify result = processEvents.process();

        if (result.dataRaceCount() > 0) {
            throw new RuntimeException(dataRaces(result.dataRaceCount(),reportDirectory));
        }
    }

    public String setup(File buildDir) {
        File agentDirectory = new File(buildDir, SetupAgent.AGENT_DIRECTORY);
        SetupAgent setupAgent = new SetupAgent(agentDirectory, "");
        EventDirectoryAndArgLine commandLineAndEventDir = setupAgent.setup();
        return commandLineAndEventDir.argLine();
    }

}
