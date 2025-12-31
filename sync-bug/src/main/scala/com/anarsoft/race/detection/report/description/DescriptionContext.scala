package com.anarsoft.race.detection.report.description

import com.anarsoft.race.detection.report.element.LoopRunAndThreadIndex

trait DescriptionContext {
  
  def threadName(key: LoopRunAndThreadIndex): String
  def methodName(key: Int): String
  def classNameForMethodId(key: Int) : String

  def methodNameWithoutSource(key: Int): String
  def fieldName(key: Int): String
  def loopName(key: Int): String
  def reportAsSummaryThreshold(loopId: Int): Int
  
}
