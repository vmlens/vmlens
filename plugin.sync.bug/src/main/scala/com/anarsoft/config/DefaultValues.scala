package com.anarsoft.config

import java.util.LinkedList


class DefaultValues {
  
  def getOnlyTraceIn()  = new LinkedList[String]; //  List[String]().asJava
	def getDoNotTraceIn() = new LinkedList[String];
  
	
	
  	def getExcludeFromTrace() =
  	{
  	   val list = new LinkedList[String];
  	   list.add("org.junit.**");
  	   list.add("junit.framework.**");
  	   list.add("sun.reflect.**");
  	   list.add("java.lang.reflect.**");
  	   
  	   list;
  	}
  	  
  
}