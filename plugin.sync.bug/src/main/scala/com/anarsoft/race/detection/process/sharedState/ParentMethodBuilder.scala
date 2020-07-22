package com.anarsoft.race.detection.process.sharedState

// val methodOrdinal: MethodOrdinal, val group: Int,val stackTraceOrdinalSet : Set[StackTraceOrdinal]

import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.HashSet

trait ParentMethodBuilder {
  
  def stackTraceOrdinalSet :  HashSet[StackTraceOrdinal];
  
  
}