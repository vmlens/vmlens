package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.HashSet


class ParentMethodBuilderMonitor( val methodOrdinal: MethodOrdinal , val reverseGroup : Int , val methodContainsMonitor : Boolean ) extends ParentMethodBuilder {
  
  val stackTraceOrdinalSet = new  HashSet[StackTraceOrdinal]();
  
  
  def create( maxGroup : Int ) =
  {
    new ParentMethodWithMonitor( methodOrdinal , maxGroup -  reverseGroup -1 , stackTraceOrdinalSet.toSet , methodContainsMonitor);
  }
  
}