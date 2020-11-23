package com.anarsoft.config

import java.util.Properties
import java.util.List
import collection.JavaConverters._
import java.util.LinkedList
import java.util.StringTokenizer
import com.anarsoft.trace.agent.runtime.util.AgentKeys._;

object PropertyTransformer {
  
  

  
  // val onlyTraceIn : List[String], val doNotTraceIn : List[String], val suppressIssues : List[String] , val mode : String
  // val onlyTraceIn : List[String], val doNotTraceIn : List[String], val excludeFromTrace : List[String],val suppressIssues : List[String] , val mode : String
  
  def createConfigValues(propetyContainer : PropetyContainer) =
  {
    new ConfigValuesFromProperties( string2List ( propetyContainer.getProperty(TRACE) ) ,  string2List ( propetyContainer.getProperty(DO_NOT_TRACE_IN) ) ,  string2List ( propetyContainer.getProperty(EXCLUDE_FROM_TRACE) ) , string2List ( propetyContainer.getProperty(SUPPRESS) ) 
        ,  propetyContainer.getProperty(MODE) );
  }
  
  
  
  
  
  def create(config : ConfigValues) =
  {
     val properties = new Properties();
     
     
  
     
  
     properties.put(DO_NOT_TRACE_IN, listToString (config.getDoNotTraceIn()));
     properties.put(TRACE, listToString (config.getOnlyTraceIn()));
     
     
     properties.put(EXCLUDE_FROM_TRACE, listToString (config.getExcludeFromTrace()));
     properties.put(SUPPRESS, listToString (config.getSuppressIssues()));
     
     properties.put(AGENT_LOG, config.getAgentLog().toString());
     properties.put(AGENT_LOG_PERFORMANCE, config.getAgentLogPerformance().toString());
      
       properties.put(AGENT_LOG_EXCEPTION, config.getAgentExceptionLog().toString());
     
   //  properties.put(PARALLELIZE, listToString (config.getParallelize()));
        
     
     
  
     
     properties;
  }
  
  
  def string2List(text : String) =
  {
    val list = new LinkedList[String]
       
    if(text != null)
    {
    
    
    val st = new StringTokenizer(text , ";");
    
    while (st.hasMoreTokens()) {
           
        	val current = st.nextToken();
        	
        	if( ! current.equals("") ) 
        	{
        		list.add( current);
        	}
        	
         }
    }
    
    list;
  }
  
  
  
  
  def listToString(list : List[String]) =
  {
    if( list == null)
    {
      "";
    }
    else
    {
      
    
    
    var text = "";
    
    var isFirst = true;
    
    val iter = list.iterator();
    
    while(  iter.hasNext())
    {
      val elem = iter.next();
      
      if( ! isFirst )
      {
        text = text + ";";
      }
      
      text = text + elem;
      
      isFirst = false;
    }
    
    text;
    
    }
  }
  
  def main(args : Array[String])
  {
    println(  listToString(null) );
    println(  listToString( scala.List[String]().asJava ) );
    
    println(  listToString( scala.List("aa").asJava ) );
    println(  listToString( scala.List("aa" , "bb").asJava ) );
    println(  listToString( scala.List("aa" , "bb" , "cc").asJava ) );
    
  }
  
  
  
}