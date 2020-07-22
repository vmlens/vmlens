package com.anarsoft.plugin.preferences

import com.vmlens.trace.agent.bootstrap.mode.ModeNames
import com.anarsoft.config.DefaultValues
import com.anarsoft.config.PropertyTransformer

object PluginDefaultValues {
  
  val MODE = ModeNames.INTERLEAVE;
  
  
  val default = new DefaultValues();
  
  /*
   *         val trace = conf.getAttribute(   AgentKeys.TRACE ,   PluginDefaultValues.defaultValue(AgentKeys.TRACE , mode)  );
        val listDoNotTraceIn =  conf.getAttribute(   AgentKeys.DO_NOT_TRACE_IN ,PluginDefaultValues.defaultValue(AgentKeys.DO_NOT_TRACE_IN , mode) );
        val excludeFromStackTrace =  conf.getAttribute(   AgentKeys.EXCLUDE_FROM_TRACE ,PluginDefaultValues.defaultValue(AgentKeys.EXCLUDE_FROM_TRACE , mode) );
        val suppress =  conf.getAttribute(   AgentKeys.SUPPRESS ,PluginDefaultValues.defaultValue(AgentKeys.SUPPRESS , mode) );
        
         def getOnlyTraceIn()  = List[String]().asJava
	def getDoNotTraceIn() = List[String]().asJava
  
	
	
  	def getExcludeFromTrace()
        
   */
  
  val  TRACE = PropertyTransformer.listToString(  default.getOnlyTraceIn());

  
  val DO_NOT_TRACE_IN =  PropertyTransformer.listToString( default.getDoNotTraceIn());
  
  val EXCLUDE_FROM_TRACE =  PropertyTransformer.listToString( default. getExcludeFromTrace())
  
  val SUPPRESS = "";
  
    
    
  
  
}