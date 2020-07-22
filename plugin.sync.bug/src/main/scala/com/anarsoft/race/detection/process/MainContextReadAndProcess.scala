package com.anarsoft.race.detection.process


import com.anarsoft.race.detection.process.monitor._;
import com.anarsoft.race.detection.process.result._;
import com.anarsoft.race.detection.process.syncAction._;
import com.anarsoft.race.detection.process.field._;
import com.anarsoft.race.detection.process.aggregate.ContextBuildAggregate
import com.anarsoft.race.detection.process.facade.ContextFacade
import  com.anarsoft.race.detection.process.state.ContextClassName
import com.anarsoft.race.detection.process.mode.state.ContextFieldAndArrayFacade4State
/**
 * 
 * The main context
 * 
 */


class MainContextReadAndProcess extends
      ContextReadMethodAndField
     with ContextCreateModelFacade  
     with ContextStackTraceGraphBuilder   
     with ContextLastProgramCounter  
     with NoOpContext
     with ContextBuildAggregate
     with ContextFieldAndArrayFacade4State
     with ContextReadAgentLog
     with ContextProcessTemplate
     with ContextReadDescription
     with ContextClassName
{
  
}