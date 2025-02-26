package com.vmlens;

import com.anarsoft.race.detection.main.ProcessEvents;
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
        new ProcessEvents(AgentMojo.eventDirectory().toPath(), reportDirectory.toPath()).process();
    }

}
