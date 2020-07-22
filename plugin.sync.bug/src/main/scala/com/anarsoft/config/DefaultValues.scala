package com.anarsoft.config

import collection.JavaConverters._


class DefaultValues {
  
  def getOnlyTraceIn()  = List[String]().asJava
	def getDoNotTraceIn() = List[String]().asJava
  
	
	
  	def getExcludeFromTrace() = List("org.junit.**" , "junit.framework.**" ,"sun.reflect.**" , "java.lang.reflect.**" ).asJava

  
  
}