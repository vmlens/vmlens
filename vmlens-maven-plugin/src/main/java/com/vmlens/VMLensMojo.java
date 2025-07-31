package com.vmlens;

import com.anarsoft.race.detection.main.ProcessEvents;
import com.anarsoft.race.detection.process.run.ProcessRunContextBuilder;
import com.vmlens.report.ResultForVerify;
import com.vmlens.setup.EventDirectoryAndArgLine;
import com.vmlens.setup.SetupAgent;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.surefire.SurefireMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;

import static com.vmlens.setup.SetupAgent.AGENT_DIRECTORY;
import static com.vmlens.setup.SetupAgent.REPORT_DIRECTORY;

@Mojo(  name = "test",
        defaultPhase = LifecyclePhase.TEST,
        threadSafe = true,
        requiresDependencyResolution = ResolutionScope.TEST)
public class VMLensMojo extends SurefireMojo {

    private File eventDirectory;

    /**
     * Agent directory
     */
    @Parameter(defaultValue = "${project.build.directory}/" + AGENT_DIRECTORY)
    protected File agentDirectory;

    @Parameter(defaultValue = "${project.build.directory}/" + REPORT_DIRECTORY)
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
        EventDirectoryAndArgLine setup = new SetupAgent(agentDirectory, getArgLine()).setup();
        getLog().info(setup.argLine());
        setArgLine(setup.argLine());
        eventDirectory = setup.eventDirectory();
    }

    protected void createReport(MojoFailureException mojoFailureException) throws MojoFailureException {
        ResultForVerify result = new ProcessEvents(eventDirectory.toPath(),
                reportDirectory.toPath(),
                new ProcessRunContextBuilder().build()).process();

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
