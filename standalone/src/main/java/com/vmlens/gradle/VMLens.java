package com.vmlens.gradle;

import com.anarsoft.race.detection.main.ProcessEvents;
import com.vmlens.report.ResultForVerify;
import com.vmlens.report.assertion.OnDescriptionAndLeftBeforeRightNoOp;
import com.vmlens.report.assertion.OnEventNoOp;
import com.vmlens.setup.EventDirectoryAndArgLine;
import com.vmlens.setup.SetupAgent;

import java.io.File;
import java.nio.file.Path;

public class VMLens {

    public void process(File buildDir) {
        File agentDirectory = new File(buildDir, SetupAgent.AGENT_DIRECTORY);
        File reportDirectory = new File(buildDir, SetupAgent.REPORT_DIRECTORY);

        Path eventPath = new File(SetupAgent.eventDir(agentDirectory)).toPath();
        Path reportPath = reportDirectory.toPath();

        ProcessEvents processEvents =
                new ProcessEvents(
                        eventPath,
                        reportPath,
                        new OnDescriptionAndLeftBeforeRightNoOp(),
                        new OnEventNoOp()
                );

        ResultForVerify result = processEvents.process();

        if (result.dataRaceCount() > 0) {
            throw new RuntimeException("There are " + result.dataRaceCount() + " data races.");
        }
    }

    public String setup(File buildDir) {
        File agentDirectory = new File(buildDir, SetupAgent.AGENT_DIRECTORY);
        SetupAgent setupAgent = new SetupAgent(agentDirectory, "");
        EventDirectoryAndArgLine commandLineAndEventDir = setupAgent.setup();
        return commandLineAndEventDir.argLine();
    }

}
