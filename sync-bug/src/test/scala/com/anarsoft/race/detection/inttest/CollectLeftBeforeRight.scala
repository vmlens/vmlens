package com.anarsoft.race.detection.inttest

import com.vmlens.report.assertion.{LeftBeforeRight, OnDescriptionAndLeftBeforeRight}
import com.vmlens.trace.agent.bootstrap.description.{ClassDescription, ThreadOrLoopDescription}

import scala.collection.mutable

class CollectLeftBeforeRight(val collected : mutable.HashSet[LeftBeforeRight])  extends OnDescriptionAndLeftBeforeRight {
  
  
  override def startRun(loopId: Int, runId: Int): Unit = {
    
  }

  override def onLeftBeforeRight(leftBeforeRight: LeftBeforeRight): Unit = {
    collected.add(leftBeforeRight)
  }

  override def onClassDescription(classDescription: ClassDescription): Unit = {
    
  }

  override def onThreadOrLoopDescription(threadOrLoopDescription: ThreadOrLoopDescription): Unit = {
    
  }
}
