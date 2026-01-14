package com.anarsoft.race.detection.report.description

import com.anarsoft.race.detection.report.element.LoopRunAndThreadIndex
import com.vmlens.trace.agent.bootstrap.description.{ClassDescription, MethodDescription}

import scala.collection.mutable

trait DescriptionContext {
  
  def threadName(key: LoopRunAndThreadIndex): String
  def methodName(key: Int): String
  def classNameForMethodId(key: Int) : String

  def methodNameWithoutSource(key: Int): String
  def fieldName(key: Int): String
  def loopName(key: Int): String
  def reportAsSummaryThreshold(loopId: Int): Int
  
  def  idToAutomaticTestClassName :  mutable.HashMap[Int, String];
  
  def methodDescription(methodId : Int) : Option[Tuple2[ClassDescription, MethodDescription]];
  
}
