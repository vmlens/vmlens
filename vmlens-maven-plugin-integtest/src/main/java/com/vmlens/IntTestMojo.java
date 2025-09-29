package com.vmlens;

import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;

import static com.vmlens.ProcessEvents.process;

@Mojo(
        name = "test",
        defaultPhase = LifecyclePhase.TEST,
        threadSafe = true,
        requiresDependencyResolution = ResolutionScope.TEST)
public class IntTestMojo extends VMLensMojo {

    @Parameter(defaultValue = "${project.build.directory}/vmlens-agent/vmlens")
    private File eventDirectory;

    @Parameter(defaultValue = "${project.build.directory}/vmlens-report")
    private File reportDirectory;

    @Override
    protected void createReport(MojoFailureException mojoFailureException) throws MojoFailureException {
        process(eventDirectory,reportDirectory);
        if(mojoFailureException != null) {
            throw mojoFailureException;
        }
    }
}
