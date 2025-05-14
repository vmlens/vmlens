package com.vmlens.setup;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Setup {

    public static final String AGENT_DIRECTORY = "vmlens-agent";
    public static final String REPORT_DIRECTORY = "vmlens-report";

    private static final String EVENT_DIRECTORY = "/vmlens/";
    private static final String[] jars = new String[]{"agent_bootstrap.jar", "agent_runtime.jar", "agent.jar"};

    private final File agentDirectory;
    private final String argLine;

    public Setup(File agentDirectory,
                 String argLine) {
        this.agentDirectory = agentDirectory;
        this.argLine = argLine;
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

            Properties properties = new Properties();
            String dir = eventDir(agentDirectory);

            File eventDir = new File(dir);
            eventDir.mkdirs();

            properties.setProperty("eventDir", dir);
            FileOutputStream stream = new FileOutputStream(agentDirectory.getAbsolutePath() + "/run.properties");
            properties.store(stream, "");
            stream.close();

            String additionalArgs = argLine;
            if (additionalArgs == null) {
                additionalArgs = "";
            }

            String agentVmArg = "-javaagent:\"" +
                    agentDirectory.getAbsolutePath() + "/agent.jar\"  " + additionalArgs;

            return new EventDirectoryAndArgLine(eventDir,agentVmArg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reCreate(File directory) {
        FileUtils.deleteQuietly(directory);
        directory.mkdirs();
    }

    public static String eventDir(File agentDirectory) {
        return agentDirectory.getAbsolutePath() + EVENT_DIRECTORY;
    }

}
