package com.vmlens;

import com.vmlens.setup.EventDirectoryAndArgLine;
import com.vmlens.setup.Setup;
import picocli.CommandLine;

@CommandLine.Command(name = "install", description = "Creates the agent directory. Print the vm parameter to System.out")
public class Install implements Runnable {

    @CommandLine.ParentCommand
    private Standalone parent;

    @Override
    public void run() {
        EventDirectoryAndArgLine result = new Setup(parent.agentDirectory, "").setup();
        System.out.println("use " + result.argLine() + "as vm parameter");
    }
}
