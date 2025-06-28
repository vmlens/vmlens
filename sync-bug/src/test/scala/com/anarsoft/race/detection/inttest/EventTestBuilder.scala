package com.anarsoft.race.detection.inttest

import com.vmlens.trace.agent.bootstrap.lock.LockType
import com.anarsoft.race.detection.event.interleave.ConditionNotifyEvent
import com.vmlens.report.assertion.Position
import com.vmlens.trace.agent.bootstrap.barriertype.{BarrierType, BarrierTypeCollection, BarrierKeyTypeCollection, BarrierKeyType}
import com.anarsoft.race.detection.event.gen.BarrierEventGen

class EventTestBuilder(val runDataTestBuilder: RunDataTestBuilder, val threadIndex: Int) {

  var methodCounter = 0;

  def conditionWaitEnter(objectHashCode: Long, lockType: LockType): Position = {
    val position = new Position(threadIndex, runDataTestBuilder.getAndIncrementRunPosition());

    position;
  }

  def conditionWaitExit(): Position = {
    val position = new Position(threadIndex, runDataTestBuilder.getAndIncrementRunPosition());

    position;
  }

  def conditionNotify(): Position = {
    val position = new Position(threadIndex, runDataTestBuilder.getAndIncrementRunPosition());

    position;
  }

  def barrier(barrierType: BarrierType, barrierKeyType: BarrierKeyType, objectHashCode: Long): Position = {
    val position = new Position(threadIndex, runDataTestBuilder.getAndIncrementRunPosition());
    val barrierEvent = new BarrierEventGen(threadIndex,
      methodCounter,
      objectHashCode,
      BarrierTypeCollection.SINGLETON.toId(barrierType),
      BarrierKeyTypeCollection.SINGLETON.toId(barrierKeyType),
      0,
      0,
      runDataTestBuilder.loopId,
      runDataTestBuilder.runId,
      runDataTestBuilder.getAndIncrementRunPosition())
    barrierEvent.addToContext(runDataTestBuilder.loadedInterleaveActionContext)

    position;
  }

  def getAndIncrementMethodCounter(): Int = {
    val temp = methodCounter;
    methodCounter = methodCounter + 1;
    temp;
  }

}
