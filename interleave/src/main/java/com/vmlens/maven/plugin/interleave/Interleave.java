package com.vmlens.maven.plugin.interleave;

import java.io.File;


import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import com.anarsoft.config.DefaultValues;

import com.vmlens.maven.plugin.AbstractMavenPlugin;
import com.vmlens.maven.plugin.Mode;


/**
 * 
 * calls vmlens
 * 
 * @author thomas
 *
 */

@Mojo(name = "test", defaultPhase = LifecyclePhase.TEST, threadSafe = false, requiresDependencyResolution = ResolutionScope.TEST)
public class Interleave extends AbstractMavenPlugin {

	@Override
	protected Mode getMode() {

		return Mode.interleave;
	}

	
	
	@Parameter(defaultValue = "${project.build.directory}/interleave")
	File reportsDirectory;

	public File getReportsDirectory() {
		return reportsDirectory;
	}

	public void setReportsDirectory(File reportsDirectory) {
		this.reportsDirectory = reportsDirectory;
	}

	@Override
	protected DefaultValues defaults() {
		
		return new DefaultValues();
	}

}
