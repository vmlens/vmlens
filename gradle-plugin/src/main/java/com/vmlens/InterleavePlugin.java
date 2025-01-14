package com.vmlens;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskProvider;

;

public class InterleavePlugin implements Plugin<Project> {

	public void apply(Project project) {

        TaskProvider<InterleaveTask> testTask = project.getTasks().register("control", InterleaveTask.class);

		
		
		
		testTask.configure(new Action() {

			@Override
			public void execute(Object arg0) {

				InterleaveTask test = (InterleaveTask) arg0;
				
				test.shouldRunAfter("test");
				

			}
		});

	}

}
