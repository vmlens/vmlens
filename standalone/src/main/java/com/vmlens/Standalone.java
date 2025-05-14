package com.vmlens;

import picocli.CommandLine;

import java.io.File;

import static com.vmlens.Standalone.DESCRIPTION;
import static com.vmlens.setup.Setup.AGENT_DIRECTORY;
import static com.vmlens.setup.Setup.REPORT_DIRECTORY;

@CommandLine.Command(name = "", description = DESCRIPTION, subcommands = {
        Install.class,
        Report.class
})
public class Standalone {

    public static final String DESCRIPTION =    "Run tests with vmlens.\n" +
                                                "1) Call install to create the agent directory. This also prints the vm parameter to System.out.\n" +
                                                "2) Run your test with the given vm parameter.\n" +
                                                "3) Call report to create the reports and check for data races.";

    @CommandLine.Option(names = {"-a", "--agent"},
            defaultValue = AGENT_DIRECTORY,
            description = "The agent directory. Default: " + AGENT_DIRECTORY)
    File agentDirectory;

    @CommandLine.Option(names = {"-r", "--report"},
            defaultValue = REPORT_DIRECTORY,
            description = "The report directory. Default: " + REPORT_DIRECTORY)
    File reportDirectory;


    public static void main(String[] args) {
        int exitCode = new CommandLine(new Standalone()).execute(args);
        System.exit(exitCode);
    }

}