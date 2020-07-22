package com.anarsoft.config

import java.io._
import java.util.List
import com.vmlens.maven.plugin.Group


/*
 * 	 @Parameter
	  List<String> onlyTraceIn;
	 
	 @Parameter
	 List<String> doNotTraceIn;
	
	 @Parameter
	 List<String> excludeFromTrace;
 */


trait MavenMojo extends ConfigValues {
  
 
  
  
  def	 getTestDir() : String;
	def  getReportDir() : File;



	
	

	


 
	
	
	
}