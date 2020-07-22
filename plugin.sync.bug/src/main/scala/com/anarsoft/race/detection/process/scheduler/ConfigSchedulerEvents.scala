package com.anarsoft.race.detection.process.scheduler


import com.anarsoft.race.detection.process.perEventList.PerEventListStepCollection
import com.anarsoft.race.detection.process.setStacktraceOrdinal.PerEventListSetStacktraceOrdinal


object ConfigSchedulerEvents {
  
  
    def initializePerEventListSteps(perEventListSteps : PerEventListStepCollection)
  {
    
    perEventListSteps.setStacktraceOrdinal.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal( ( context ) => context.atomicMethodEventList   ) )
    perEventListSteps.setStacktraceOrdinal.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal(( context ) => context.atomicMethodEventList ) )
    perEventListSteps.setStacktraceOrdinal.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal(( context ) => context.atomicMethodEventList ) )
    
    
  } 
  
}