package com.anarsoft.race.detection.process


import com.anarsoft.race.detection.process.method._;
import com.anarsoft.race.detection.process.field._;
import com.anarsoft.race.detection.process.syncAction.ContextProcessSyncAction
import com.anarsoft.race.detection.process.nonVolatileField._;
import  com.anarsoft.race.detection.process.directMemory.ContextReadDirectMemory;
import com.anarsoft.race.detection.process.detectRaceConditions.ContextDetectRaceConditions
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo
import com.anarsoft.race.detection.process.interleave.ContextInterleave
import com.anarsoft.race.detection.process.scheduler.ContextSchedulerRead

trait ContextReadMethodAndField extends ContextMethodData  
  with ContextProcessSyncAction 
 with ContextReadDirectMemory 
with ContextReadAndProzessSyncActionAndMonitorEvents
with ContextNonVolatileFields
with ContextDetectRaceConditions
with ContextSetMonitorInfo
  with ContextInterleave
  with ContextSchedulerRead
  with ContextAddNonVolatileFields2InterleaveList
{
  
}