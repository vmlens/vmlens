package com.vmlens;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.gradle.api.Action;
import org.gradle.api.GradleScriptException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskProvider;

import com.anarsoft.config.MavenMojo;
import com.vmlens.api.callback.ApiCallbackParallize;
import com.vmlens.api.callback.ExtractAgentAndCheckLicence;
import com.vmlens.api.callback.IssuesFoundException;
import com.vmlens.api.internal.reports.ReportFacade$;;

public class InterleavePlugin implements Plugin<Project> {

	public void apply(Project project) {

		 TaskProvider<InterleaveTask> testTask = project.getTasks().register("interleave", InterleaveTask.class);

		
		
		
		testTask.configure(new Action() {

			@Override
			public void execute(Object arg0) {

				InterleaveTask test = (InterleaveTask) arg0;
				
				test.shouldRunAfter("test");
				

				test.doFirst(new Action() {

					@Override
					public void execute(Object a) {

						InterleaveTask intern = (InterleaveTask) a;

						intern.agentDirectory.mkdirs();
						intern.reportDirectory.mkdirs();
						
					

						FileUtils.deleteQuietly(new File(intern.agentDirectory.getAbsoluteFile() + "/vmlens"));

						MavenMojo mavenMojo = new MavenMojoForGradle(intern);

						intern.source = (new ExtractAgentAndCheckLicence())
								.extractAndCheckAndSetPropertiesInRunProperties(intern.agentDirectory, mavenMojo);
						
						intern.jvmArgs( "-javaagent:" + 	intern.agentDirectory.getAbsolutePath() + "/agent.jar" );

					}

				}

				);
				
				
				test.doLast(new Action() {

					@Override
					public void execute(Object a) {

						InterleaveTask intern = (InterleaveTask) a;

					
					
				

						MavenMojo mavenMojo = new MavenMojoForGradle(intern);

						if (!intern.deadlockAndDataRaceDetection) {

							ReportFacade$.MODULE$.saveDeadlockAndDataRaceDetectionFalse(mavenMojo.getReportDir());

							return;
						}

						 try {
							new ApiCallbackParallize().prozess(intern.source, mavenMojo, new GradleProgressMonitor(intern.getLogger()));
						} catch (IssuesFoundException e) {
						
							throw new GradleScriptException(e.getMessage() , e);
							
						}

					}

				}

				);
				
				

			}

		});

	}

}
