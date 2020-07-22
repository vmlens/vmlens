package com.anarsoft.race.detection.process

import com.anarsoft.config.ConfigValues;

class TestConfigValues extends ConfigValues {
  
  def getOnlyTraceIn() = null;
	def getDoNotTraceIn() = null;
	def getSuppressIssues() = null;
	
	def getExcludeFromTrace() = null;
	
	// erstmal herausgenommen
	//def getExcludeFromTrace() : List[String];
	
  
  def getMode() = "interleave";
  
  def getAgentLog() = false;
  def getAgentLogPerformance() = false;
  
  	def getMaximumRunCount() = 5000;

  def getParallelize()  = null;
  
    def getMaximumOperationCount() = 2000;
  
  def getDisableTimeoutCheck() = false;
	 
 def getDisableTimeoutWarningCheck()  = false;
 
  def getAgentExceptionLog()  = false;
}