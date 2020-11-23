package com.anarsoft.config

import java.util.List

trait ConfigValues {
  
  def getOnlyTraceIn() :  List[String];
	def getDoNotTraceIn() : List[String];
	def getSuppressIssues() : List[String];
	

	def getExcludeFromTrace() : List[String];
	
  
 
  
  
  def getAgentLog() : Boolean;
  def getAgentLogPerformance() : Boolean;
  
  def getAgentExceptionLog() : Boolean;
  
  

  
  def getDisableTimeoutCheck() : Boolean;
  def getDisableTimeoutWarningCheck() : Boolean;
  
  

  
  
}