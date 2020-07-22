package com.anarsoft.race.detection.process.detectDeadlocks

import com.anarsoft.race.detection.model.result.StackTraceOrdinalAndMonitor

class DeadlockLocation(val parent : StackTraceOrdinalAndMonitor, val child : StackTraceOrdinalAndMonitor) {
  
}