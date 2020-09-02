package com.anarsoft.race.detection.process.perEventList

import com.anarsoft.race.detection.process.aggregate._
import com.anarsoft.race.detection.process.setStacktraceOrdinal._
import  com.anarsoft.race.detection.process.detectRaceConditions._
import com.anarsoft.race.detection.process.syncAction._
import com.anarsoft.race.detection.process.field.ContextFieldIdMap
import com.anarsoft.race.detection.process.volatileField.ConfigVolatileFields
import com.anarsoft.race.detection.process.nonVolatileField.ConfigNonVolatileFields
import com.anarsoft.race.detection.process.monitor.ConfigMonitor
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo
import com.anarsoft.race.detection.process.state._;
import com.anarsoft.race.detection.process.nonVolatileField.ContextNonVolatileFields
import com.anarsoft.race.detection.process.scheduler.ConfigSchedulerEvents
import com.anarsoft.race.detection.process.arrayId.ContextSetArrayOrdinal
import com.anarsoft.race.detection.process.nonVolatileField.ArrayAccessEvent



class PerEventListStepCollection( ) {
  
  val buildMethodOrdinalAggregate  = new StepProcessPerEventListCollectionWithName[ContextNonVolatileFields](classOf[ContextNonVolatileFields], "buildMethodOrdinalAggregate");
  
//  val newBuildMethodOrdinalAggregate  = new StepProcessPerEventListCollectionWithName[ContextBuildMethodOrdinalAggregate](classOf[ContextBuildMethodOrdinalAggregate], "newBuildMethodOrdinalAggregate");
  
  
  val buildStackTraceOrdinalAggregate  = new StepProcessPerEventListCollectionWithName[ContextBuildStackTraceOrdinal4State](classOf[ContextBuildStackTraceOrdinal4State], "buildStackTraceOrdinalAggregate")
  
  
  val setStacktraceOrdinal =  new StepProcessPerEventListCollection[ContextSetStacktraceOrdinal](classOf[ContextSetStacktraceOrdinal]) 
  
  
  val setStacktraceOrdinal4OwnerOfState =  new StepProcessPerEventListCollection[ContextSetStacktraceOrdinal4OwnerOfState](classOf[ContextSetStacktraceOrdinal4OwnerOfState]) 
  
  
  //val  checkOwner4State = new StepProcessPerEventListCollection[ContextCheckOwner4State](classOf[ContextCheckOwner4State]) 
  
  
  
  val detectRaceConditions =  new StepProcessPerEventListCollection[ContextDetectRaceConditions](classOf[ContextDetectRaceConditions]) 
  
   val setFieldOrdinal =  new StepProcessPerEventListCollection[ContextFieldIdMap](classOf[ContextFieldIdMap]) 
  
  
  val prozessSyncPointLists = new StepProzessSyncPointLists();
  
  val setMonitorInfo4NonVolatile =  new StepProcessPerEventListCollection[ContextSetMonitorInfo](classOf[ContextSetMonitorInfo]) 
  
  
  val setMonitorInfo4Volatile =  new StepProcessPerEventListCollection[ContextSetMonitorInfo](classOf[ContextSetMonitorInfo]) 
   
  
  
  
   val setSortable =  new StepProcessPerEventListCollection[ContextSetSortable](classOf[ContextSetSortable]) 
   
   
   val setArrayOrdinalInterleave =  new StepProcessPerEventListCollection[ContextSetArrayOrdinal[ArrayAccessEvent]](classOf[ContextSetArrayOrdinal[ArrayAccessEvent]]) 
   
    val setArrayOrdinalState =  new StepProcessPerEventListCollection[ContextSetArrayOrdinal[StateEventArray]](classOf[ContextSetArrayOrdinal[StateEventArray]]) 
   
//   val transferSortable =  new StepProcessPerEventListCollection[ContextSetSortable](classOf[ContextSetSortable]) 
  
 
    
 
  
  
    
}

object PerEventListStepCollection
{
  
  def apply() =
  {
    val perEventListSteps = new PerEventListStepCollection();
    
    ConfigVolatileFields.initializePerEventListSteps(perEventListSteps);
    ConfigNonVolatileFields.initializePerEventListSteps(perEventListSteps);
    ConfigSchedulerEvents.initializePerEventListSteps(perEventListSteps);
    
    
    ConfigMonitor.initializePerEventListSteps(perEventListSteps);
    ConfigLocks.initializePerEventListSteps(perEventListSteps);
    
    perEventListSteps;
  }
  
  
  
 
  def create4State() =
  {
        val perEventListSteps = new PerEventListStepCollection();
    
      ConfigState.initializePerEventListSteps(perEventListSteps);

    
    perEventListSteps;
  }
//  
  
  
  
  
  
}