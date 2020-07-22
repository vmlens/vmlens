package com.anarsoft.race.detection.model.result

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet
import scala.collection.mutable.HashMap


class ModelFacadeMonitor  (val  sharedStateCollection : ArrayBuffer[MethodWithMonitor] ,
    val stackTraceGraph : StackTraceGraph ,
     val threadNames : ThreadFacade,
       val deadlocks : HashSet[Deadlock] ,
       val  ownerId2Name : HashMap[Int,String] ,
       val stackTraceGraphMonitorAnnotation : StackTraceGraphMonitorAnnotation,
        val agentLog : ArrayBuffer[String]
    ) extends ModelFacade {
  
  
  def hasIssues()  = ! deadlocks.isEmpty
  
  
  
}