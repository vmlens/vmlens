package com.vmlens;

import com.anarsoft.race.detection.main.ProcessEvents;
import com.vmlens.report.ResultForVerify;
import com.vmlens.report.assertion.OnDescriptionAndLeftBeforeRightNoOp;
import com.vmlens.report.assertion.OnEventNoOp;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;

@Mojo(name = "verify", defaultPhase = LifecyclePhase.VERIFY,
        requiresDependencyResolution = ResolutionScope.NONE, threadSafe = true)
public class VerifyMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.directory}/vmlens-report")
    private File reportDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        ResultForVerify result = new ProcessEvents(AgentMojo.eventDirectory().toPath(),
                reportDirectory.toPath(),
                new OnDescriptionAndLeftBeforeRightNoOp(), new OnEventNoOp()).process();

        handleResult(result,reportDirectory);
    }

    // visible for test
    static void handleResult(ResultForVerify result, File reportDirectory) throws MojoFailureException {
        if(result.failureCount() > 0 && result.dataRaceCount() > 0 ) {
            throw new MojoFailureException(String.format("There are %s test failures and %s data races, see %s for the test report." ,
                    result.failureCount(),result.dataRaceCount(),reportDirectory.toString()));
        }
        if(result.failureCount() > 0 ) {
            throw new MojoFailureException(String.format("There are %s test failures, see %s for the report." ,
                    result.failureCount(),reportDirectory.toString()));
        }
        if(result.dataRaceCount() > 0 ) {
            throw new MojoFailureException(String.format("There are %s data races, see %s for the report.",
                    result.dataRaceCount(),reportDirectory.toString()));
        }
    }

}
