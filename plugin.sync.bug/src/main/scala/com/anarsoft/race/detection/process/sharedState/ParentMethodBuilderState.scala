package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.HashSet

class ParentMethodBuilderState( val methodOrdinal: MethodOrdinal , val reverseGroup : Int  ) extends ParentMethodBuilder {
  
  val stackTraceOrdinalSet = new  HashSet[StackTraceOrdinal]();
  
  
  def create( maxGroup : Int ) =
  {
    new ParentMethod( methodOrdinal , maxGroup -  reverseGroup -1 , stackTraceOrdinalSet.toSet);
  }
  
}