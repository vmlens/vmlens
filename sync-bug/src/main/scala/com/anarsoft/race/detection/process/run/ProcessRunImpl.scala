package com.anarsoft.race.detection.process.run

import com.anarsoft.race.detection.createlastthreadposition.LastThreadPositionMap
import com.anarsoft.race.detection.createstacktrace.ServiceCalculateMethodCountToStacktraceNode
import com.anarsoft.race.detection.groupnonvolatile.GroupNonVolatileMemoryAccessElementForResult
import com.anarsoft.race.detection.loopAndRunData.{RunData, RunResult, RunResultImpl}
import com.anarsoft.race.detection.partialorder.{BuildPartialOrderContextImpl, PartialOrderContainer, PartialOrderImpl}
import com.anarsoft.race.detection.process.main.ProcessRun
import com.vmlens.report.assertion.OnDescriptionAndLeftBeforeRight

import scala.collection.mutable.ArrayBuffer

class ProcessRunImpl(val onTestLoopAndLeftBeforeRight : OnDescriptionAndLeftBeforeRight) extends ProcessRun {

  def process(runData: RunData): RunResult = {
    onTestLoopAndLeftBeforeRight.startRun(runData.loopAndRunId.loopId,runData.loopAndRunId.runId);
    
    // calculate the stacktrace map
    val stackTraceIdToStacktrace = new ServiceCalculateMethodCountToStacktraceNode().process(runData.methodEventArray);
    val stackTraceMap = stackTraceIdToStacktrace.toMap;

    // set the stacktrace nodes in the events
    for (syncActionElement <- runData.interLeaveElements) {
      syncActionElement.setStacktraceNode(stackTraceMap)
    }
    for (nonVolatileElement <- runData.nonVolatileElements) {
      nonVolatileElement.setStacktraceNode(stackTraceMap)
    }

    // calculate last thread position
    val lastThreadPositionMap = new LastThreadPositionMap();
    for (syncActionElement <- runData.interLeaveElements) {
      syncActionElement.addLastThreadPosition(lastThreadPositionMap)
    }
    for (nonVolatileElement <- runData.nonVolatileElements) {
      nonVolatileElement.addLastThreadPosition(lastThreadPositionMap)
    }
    
    // calculate the partial order
    val partialOrderContainer = new PartialOrderContainer(onTestLoopAndLeftBeforeRight);
    val buildPartialOrderContext = new BuildPartialOrderContextImpl(partialOrderContainer,lastThreadPositionMap);
    for (syncActionElement <- runData.interLeaveElements) {
      syncActionElement.addToPartialOrderBuilder(buildPartialOrderContext);
    }

    // sort the non volatile events according to the partial order
    val partialOrder = new PartialOrderImpl(partialOrderContainer);
    val nonVolatileResult = new ArrayBuffer[GroupNonVolatileMemoryAccessElementForResult]();
    for (nonVolatileElement <- runData.nonVolatileElements) {
      nonVolatileResult.append(nonVolatileElement.sort(partialOrder));
    }
    
    new RunResultImpl(runData.loopAndRunId, nonVolatileResult.toList, runData.controlEvents,
      runData.interLeaveElements);
  }
}
