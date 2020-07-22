package com.vmlens.maven.plugin;


import org.apache.maven.plugin.logging.Log;
import com.anarsoft.race.detection.process.workflow.ProgressMonitor;


public class MavenProgressMonitor implements ProgressMonitor {

	private final Log log;
	private int overallWorkProcessed = 0;
	
	public MavenProgressMonitor(Log log) {
		super();
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
