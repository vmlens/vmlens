package com.vmlens;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.gradle.api.GradleScriptException;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.testing.Test;

import com.anarsoft.config.DefaultValues;
import com.anarsoft.config.MavenMojo;
import com.vmlens.api.callback.ApiCallbackParallize;
import com.vmlens.api.callback.ExtractAgentAndCheckLicence;
import com.vmlens.api.callback.IssuesFoundException;
import com.vmlens.api.internal.reports.ReportFacade$;
import com.vmlens.maven.plugin.AbstractMavenPlugin;

public class InterleaveTask extends Test {

	// parameter:
	boolean deadlockAndDataRaceDetection = true;
	File agentDirectory;
	File reportDirectory;
	List<String> doNotTraceIn;
	List<String> excludeFromTrace;
	List<String> onlyTraceIn;
	List<String> suppress;

	public InterleaveTask() {

		agentDirectory = new File(getProject().getBuildDir().getAbsolutePath() + "/vmlens-agent");
		reportDirectory = new File(getProject().getBuildDir().getAbsolutePath() + "/reports/tests/interleave");

		DefaultValues defaultValues = new DefaultValues();

		doNotTraceIn = defaultValues.getDoNotTraceIn();
		excludeFromTrace = defaultValues.getExcludeFromTrace();
		onlyTraceIn = defaultValues.getOnlyTraceIn();
		suppress = new LinkedList<String>();

	}

	@Internal
	public File getAgentDirectory() {
		return agentDirectory;
	}

	public void setAgentDirectory(File agentDirectory) {
		this.agentDirectory = agentDirectory;
	}

	@Internal
	public File getReportDirectory() {
		return reportDirectory;
	}

	public void setReportDirectory(File reportDirectory) {
		this.reportDirectory = reportDirectory;
	}

	@Input
	public boolean isDeadlockAndDataRaceDetection() {
		return deadlockAndDataRaceDetection;
	}

	public void setDeadlockAndDataRaceDetection(boolean deadlockAndDataRaceDetection) {
		this.deadlockAndDataRaceDetection = deadlockAndDataRaceDetection;
	}

	public void doNotTraceIn(String in) {
		if (in != null && !in.trim().isEmpty()) {
			doNotTraceIn.add(in);
		}

	}

	public void excludeFromTrace(String in) {
		if (in != null && !in.trim().isEmpty()) {
			excludeFromTrace.add(in);
		}

	}

	public void onlyTraceIn(String in) {
		if (in != null && !in.trim().isEmpty()) {
			onlyTraceIn.add(in);
		}

	}

	public void suppress(String in) {
		if (in != null && !in.trim().isEmpty()) {
			suppress.add(in);
		}

	}

	private void proccessResult(String source, MavenMojo mavenMojo) throws IssuesFoundException {
		if (!deadlockAndDataRaceDetection) {

			ReportFacade$.MODULE$.saveDeadlockAndDataRaceDetectionFalse(mavenMojo.getReportDir());

			return;
		}

		if (AbstractMavenPlugin.noTestRun(source, mavenMojo)) {
			return;
		}

		new ApiCallbackParallize().prozess(source, mavenMojo, new GradleProgressMonitor(getLogger()));

	}

	@Override
	@TaskAction
	public void executeTests() {

		agentDirectory.mkdirs();
		reportDirectory.mkdirs();

		FileUtils.deleteQuietly(new File(agentDirectory.getAbsoluteFile() + "/vmlens"));

		MavenMojo mavenMojo = new MavenMojoForGradle(this);

		String source = (new ExtractAgentAndCheckLicence())
				.extractAndCheckAndSetPropertiesInRunProperties(agentDirectory, mavenMojo);

		jvmArgs("-javaagent:" + agentDirectory.getAbsolutePath() + "/agent.jar");

		try {
			super.executeTests();
		} catch (Throwable exp) {
			try {
				proccessResult(source, mavenMojo);
			} catch (IssuesFoundException e) {

			}

			throw exp;
		}

		try {
			proccessResult(source, mavenMojo);
		} catch (IssuesFoundException e) {

			throw new GradleScriptException(e.getMessage(), e);

		}
	}

}
