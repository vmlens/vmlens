package com.anarsoft.race.detection.report.description

import com.anarsoft.race.detection.report.element.LoopRunAndThreadIndex

trait DescriptionContext {
  
  def threadName(key: LoopRunAndThreadIndex): String
  def methodName(key: Integer): String

  def methodNameWithoutSource(key: Integer): String
  def fieldName(key: Integer): String
  def loopName(key: Integer): String
  def reportAsSummaryThreshold(loopId: Integer): Int
  
}
