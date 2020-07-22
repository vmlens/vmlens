package com.anarsoft.race.detection.process.mode.state

import com.anarsoft.race.detection.process.directMemory.ContextReadDirectMemory
import com.anarsoft.race.detection.process.syncAction.ContextProcessSyncAction
import com.anarsoft.race.detection.process.monitor.ContextMonitor
import com.anarsoft.race.detection.process.field.ContextFieldIdMap
import com.anarsoft.race.detection.process.aggregate.ContextBuildAggregate
import com.anarsoft.race.detection.process.setStacktraceOrdinal.ContextSetStacktraceOrdinal4OwnerOfState
import com.anarsoft.race.detection.process.monitorRelation.ContextCreateMonitorRelation
import com.anarsoft.race.detection.process.detectDeadlocks.ContextDetectDeadlocks
import com.anarsoft.race.detection.process.aggregate.ContextBuildMethodOrdinalAggregate
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo
import com.anarsoft.race.detection.process.method._;
import com.anarsoft.race.detection.process.field._;
import com.anarsoft.race.detection.process.syncAction.ContextProcessSyncAction
import com.anarsoft.race.detection.process.nonVolatileField._;
import  com.anarsoft.race.detection.process.directMemory.ContextReadDirectMemory;
import com.anarsoft.race.detection.process.detectRaceConditions.ContextDetectRaceConditions
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo
import com.anarsoft.race.detection.process.detectRaceConditions.ContextSetSortable
import com.anarsoft.race.detection.process.state.ContextState
import com.anarsoft.race.detection.process.state.ContextBuildStackTraceOrdinal4State
import  com.anarsoft.race.detection.process.arrayId.ContextSetArrayOrdinal
import com.anarsoft.race.detection.process.state.StateEventArray


trait ContextReadMethodAndStateEvent  
extends ContextMethodData
with ContextState
with ContextSetStacktraceOrdinal4OwnerOfState
with ContextBuildStackTraceOrdinal4State
with ContextSetArrayOrdinal[StateEventArray]
{
   def arrayIdEventList  = stateArrayEventList;
}

/*
with ContextFieldIdMap
with ContextBuildAggregate
with ContextSetStacktraceOrdinal4OwnerOfState
with ContextBuildMethodOrdinalAggregate
with ContextNonVolatileFields
with ContextSetSortable
*/