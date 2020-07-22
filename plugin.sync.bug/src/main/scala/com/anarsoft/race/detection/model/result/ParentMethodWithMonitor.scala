package com.anarsoft.race.detection.model.result

class ParentMethodWithMonitor(val methodOrdinal: MethodOrdinal, val group: Int,
    val stackTraceOrdinalSet : Set[StackTraceOrdinal],val methodContainsMonitor : Boolean ) {
  
}