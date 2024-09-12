package com.vmlens;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.*;
import java.util.Properties;


/**
 *
 */
@Mojo(name = "prepare-agent", defaultPhase = LifecyclePhase.INITIALIZE, requiresDependencyResolution = ResolutionScope.RUNTIME, threadSafe = true)
public class AgentMojo
        extends AbstractMojo {

    private static final String SUREFIRE_ARG_LINE = "argLine";
    private static final String[] jars = new String[]{"agent_bootstrap.jar", "agent_runtime.jar", "agent.jar"};

    @Parameter(property = "project", readonly = true)
    private MavenProject project;
    /**
     * Agent directory
     */
    @Parameter(defaultValue = "${project.build.directory}/vmlens-agent")
    private File agentDirectory;

    public void execute()
            throws MojoExecutionException {
        try {
            if (!agentDirectory.exists()) {
                agentDirectory.mkdirs();
            }


            for (String jar : jars) {
                File toBeDeleted = new File(agentDirectory.getAbsolutePath() + "/" + jar);
                FileUtils.deleteQuietly(toBeDeleted);
            }


            for (String jar : jars) {
                FileOutputStream target = null;

                target = new FileOutputStream(agentDirectory.getAbsolutePath() + "/" + jar);
                InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("agent_lib/" + jar);
                IOUtils.copy(input, target);
                input.close();
                target.close();
            }

            Properties properties = new Properties();
            String dir = agentDirectory.getAbsolutePath() + "/vmlens/";

            new File(dir).mkdirs();
            properties.setProperty("eventDir", dir);
            FileOutputStream stream = new FileOutputStream(new File(agentDirectory.getAbsolutePath() + "/run.properties"));
            properties.store(stream, "");
            stream.close();

            Properties projectProperties = project.getProperties();
            String additionalArgs = projectProperties.getProperty(SUREFIRE_ARG_LINE);
            if (additionalArgs == null) {
                additionalArgs = "";
            }

            String agentVmArg = "-javaagent:\"" +
                    agentDirectory.getAbsolutePath() + "/agent.jar\"  " + additionalArgs;
            getLog().info(agentVmArg);

            projectProperties.setProperty(SUREFIRE_ARG_LINE, agentVmArg);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
