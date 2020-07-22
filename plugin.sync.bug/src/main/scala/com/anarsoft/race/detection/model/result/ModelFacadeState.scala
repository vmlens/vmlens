package com.anarsoft.race.detection.model.result

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet


class ModelFacadeState( val  sharedStateCollection : ArrayBuffer[MethodWithSharedState] ,
    val  fieldAndArrayPerStackTraceFacade : FieldAndArrayPerStackTraceFacade   ,
    val stackTraceGraph : StackTraceGraph ,
     val fieldAndArrayFacade : FieldAndArrayPerMethodFacade,
     val threadNames : ThreadFacade,
       val notStateless : ArrayBuffer[NotStateless] ,
       val  ownerId2Name : HashMap[Int,String] ,
       val stackTraceGraphStateAnnotation : StackTraceGraphStateAnnotation,
       val  agentLog : ArrayBuffer[String]
    ) extends ModelFacade {
  
  
  
   def hasIssues()  = ! notStateless.isEmpty 
  
  
}