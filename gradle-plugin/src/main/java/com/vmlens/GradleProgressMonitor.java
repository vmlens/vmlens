package com.vmlens;


import org.gradle.api.logging.Logger;

import com.anarsoft.race.detection.process.workflow.ProgressMonitor;

public class GradleProgressMonitor implements ProgressMonitor {

	private int overallWorkProcessed = 0;
	private Logger log;
	
	public GradleProgressMonitor(Logger log) {
	
		this.log = log;
	}

	@Override
	public void done(int work) {
		
		overallWorkProcessed += work;
		
		log.info("vmlens-maven-plugin processed " + overallWorkProcessed + "% of the trace files.");
		
	}

	@Override
	public boolean isCanceled() {
		return false;
	}
	
	
}
