package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result.StackTraceGraph
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.model.result.MethodWithSharedState
import com.anarsoft.race.detection.model.result.FieldAndArrayPerStackTraceFacade
import com.anarsoft.race.detection.model.result.StackTraceGraphStateAnnotation
import com.anarsoft.race.detection.model.result.NotStateless
import com.anarsoft.race.detection.model.result._;



trait ContextSharedState {
  
    def stackTraceGraph : StackTraceGraph;
    def fieldAndArrayPerStackTraceFacade : FieldAndArrayPerStackTraceFacade;
    def fieldAndArrayFacade : FieldAndArrayPerMethodFacade;
    
    var sharedStateCollection : ArrayBuffer[MethodWithSharedState] = null;  
    
    var notStateless = new ArrayBuffer[NotStateless];
    
    var stackTraceGraphStateAnnotation : StackTraceGraphStateAnnotation = null;
  
}