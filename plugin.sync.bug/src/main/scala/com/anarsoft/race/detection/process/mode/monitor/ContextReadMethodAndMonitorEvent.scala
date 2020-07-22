package com.anarsoft.race.detection.process.mode.monitor

import com.anarsoft.race.detection.process.monitor.ContextMonitor
import com.anarsoft.race.detection.process.setStacktraceOrdinal.ContextSetStacktraceOrdinal4OwnerOfMonitor
import com.anarsoft.race.detection.process.monitorRelation.ContextCreateMonitorRelation
import com.anarsoft.race.detection.process.detectDeadlocks.ContextDetectDeadlocks
import  com.anarsoft.race.detection.process.method.ContextMethodData


trait ContextReadMethodAndMonitorEvent extends 
    ContextMonitor
    with ContextSetStacktraceOrdinal4OwnerOfMonitor
    with ContextCreateMonitorRelation
    with ContextDetectDeadlocks
    with ContextMethodData
{
  
}