package com.vmlens.setup;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SetupAgent {

    public static final String AGENT_DIRECTORY = "vmlens-agent";
    public static final String REPORT_DIRECTORY = "vmlens-report";

    private static final String EVENT_DIRECTORY = "/vmlens/";
    private static final String[] jars = new String[]{"agent_bootstrap.jar", "agent_runtime.jar", "agent.jar"};

    private final File agentDirectory;
    private final String argLine;

    public SetupAgent(File agentDirectory,
                      String argLine) {
        this.agentDirectory = agentDirectory;
        this.argLine = argLine;
    }

    public static void reCreate(File directory) {
        FileUtils.deleteQuietly(directory);
        directory.mkdirs();
    }

    public static String eventDir(File agentDirectory) {
        return agentDirectory.getAbsolutePath() + EVENT_DIRECTORY;
    }


    public EventDirectoryAndArgLine setup() {
        try {

            reCreate(agentDirectory);

            for (String jar : jars) {
                FileOutputStream target = null;
                target = new FileOutputStream(agentDirectory.getAbsolutePath() + "/" + jar);
                InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("agent_lib/" + jar);
                IOUtils.copy(input, target);
                input.close();
                target.close();
            }

            String dir = eventDir(agentDirectory);
            File eventDir = new File(dir);
            eventDir.mkdirs();
            return new EventDirectoryAndArgLine(eventDir,vmArg());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String vmArg() {
        String additionalArgs = argLine;
        if (additionalArgs == null) {
            additionalArgs = "";
        }

        String arg =  "-javaagent:" +
              new File( agentDirectory.getAbsolutePath() , "agent.jar").getAbsolutePath() + " " + additionalArgs;
        return arg.trim();
    }

}
