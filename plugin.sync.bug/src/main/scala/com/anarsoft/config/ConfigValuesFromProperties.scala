package com.anarsoft.config

import java.util.List
import java.util.LinkedList;


class ConfigValuesFromProperties(  val onlyTraceIn : List[String], val doNotTraceIn : List[String], val excludeFromTrace : List[String],val suppressIssues : List[String] , val mode : String) extends ConfigValues {


  def getOnlyTraceIn() = onlyTraceIn;
  def getDoNotTraceIn() = doNotTraceIn;
	def getSuppressIssues() = suppressIssues;
  
	def getExcludeFromTrace()  =  excludeFromTrace;
	
	def getMode() = mode;
	
	def getAgentLog() = false;
  def getAgentLogPerformance() = false;
  
	def getMaximumRunCount() = 5000;
	
	
	 def getParallelize()= null;
	
	 
	   def getMaximumOperationCount() = 2000;
  
  def getDisableTimeoutCheck() = false;
	 
  
    def getDisableTimeoutWarningCheck()  = false;
    
    def getAgentExceptionLog()  = false;
}