package com.vmlens;

import com.anarsoft.race.detection.main.ProcessEvents;
import com.vmlens.report.ResultForVerify;
import com.vmlens.report.assertion.OnDescriptionAndLeftBeforeRightNoOp;
import com.vmlens.report.assertion.OnEventNoOp;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.surefire.SurefireMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Mojo(
        name = "test",
        defaultPhase = LifecyclePhase.TEST,
        threadSafe = true,
        requiresDependencyResolution = ResolutionScope.TEST)
public class VMLensMojo extends SurefireMojo {

    private static final String SUREFIRE_ARG_LINE = "argLine";
    private static final String[] jars = new String[]{"agent_bootstrap.jar", "agent_runtime.jar", "agent.jar"};

    private File eventDirectory;

    /**
     * Agent directory
     */
    @Parameter(defaultValue = "${project.build.directory}/vmlens-agent")
    protected File agentDirectory;

    @Parameter(defaultValue = "${project.build.directory}/vmlens-report")
    protected File reportDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        prepareAgent();
        MojoFailureException mojoFailureException = null;
        try{
            super.execute();
        } catch(MojoFailureException exp) {
            mojoFailureException = exp;
        }

        createReport(mojoFailureException);
    }

    private void prepareAgent() {
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

            File eventDir = new File(dir);
            eventDir.mkdirs();
            eventDirectory = eventDir;
            properties.setProperty("eventDir", dir);
            FileOutputStream stream = new FileOutputStream(new File(agentDirectory.getAbsolutePath() + "/run.properties"));
            properties.store(stream, "");
            stream.close();


            String additionalArgs = getArgLine();
            if (additionalArgs == null) {
                additionalArgs = "";
            }

            String agentVmArg = "-javaagent:\"" +
                    agentDirectory.getAbsolutePath() + "/agent.jar\"  " + additionalArgs;
            getLog().info(agentVmArg);
            setArgLine(agentVmArg);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void createReport(MojoFailureException mojoFailureException) throws MojoFailureException {
        ResultForVerify result = new ProcessEvents(eventDirectory.toPath(),
                reportDirectory.toPath(),
                new OnDescriptionAndLeftBeforeRightNoOp(), new OnEventNoOp()).process();

        handleResult(result,reportDirectory,mojoFailureException,this.getLog());
    }

    // Visible for Test
    static void handleResult(ResultForVerify result,
                             File reportDirectory,
                             MojoFailureException mojoFailureException,
                             Log log) throws MojoFailureException {
        if(mojoFailureException != null) {
            log.error(String.format("See %s for the vmlens report.", reportDirectory.toString()));
            throw mojoFailureException;
        }
        if(result.dataRaceCount() > 0 ) {
            throw new MojoFailureException(String.format("There are %s data races, see %s for the report.",
                    result.dataRaceCount(),reportDirectory.toString()));
        }
        log.info(String.format("See %s for the vmlens report.", reportDirectory.toString()));
    }

}
