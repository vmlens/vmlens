package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.ArrayBuffer


trait ContextSharedMonitor {
  

  def stackTraceGraph : StackTraceGraph;
    
    var sharedStateCollection : ArrayBuffer[MethodWithMonitor] = null;  

  var stackTraceGraphMonitorAnnotation : StackTraceGraphMonitorAnnotation = null;
    
    
    
}