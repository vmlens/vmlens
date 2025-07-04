package com.vmlens;

import com.anarsoft.race.detection.main.ProcessEvents;
import com.vmlens.expected.check.BuildEventListMap;
import com.vmlens.expected.check.CheckLeftBeforeRight;
import com.vmlens.expected.check.CheckLockEnterExit;
import com.vmlens.expected.domain.TestCaseCollection;
import com.vmlens.expected.factory.TestCaseCollectionFactory;
import com.vmlens.report.LoopToDataRaceCount;
import com.vmlens.report.ResultForVerify;
import com.vmlens.report.assertion.EventForAssertion;
import com.vmlens.report.assertion.OnDescriptionAndLeftBeforeRightNoOp;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;
import java.util.*;

import static com.vmlens.ProcessEvents.process;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

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
    }
}
