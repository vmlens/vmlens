package com.anarsoft.race.detection.inttest

import com.vmlens.trace.agent.bootstrap.lock.LockType
import com.anarsoft.race.detection.event.interleave.ConditionNotifyEvent
import com.vmlens.report.assertion.Position
import com.vmlens.trace.agent.bootstrap.barriertype.{BarrierKeyType, BarrierKeyTypeCollection, BarrierType, BarrierTypeCollection}
import com.anarsoft.race.detection.event.gen.{BarrierEventGen, ConditionWaitEnterEventGen, ConditionWaitExitEventGen}
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.LockKey

class EventTestBuilder(val runDataTestBuilder: RunDataTestBuilder, val threadIndex: Int) {

  var methodCounter = 0;

  def conditionWaitEnter(lockKey : LockKey): Position = {
    val position = new Position(threadIndex, runDataTestBuilder.getAndIncrementRunPosition());
   val event = new ConditionWaitEnterEventGen(threadIndex,
      methodCounter,
     lockKey.objectHashCode(),
      0, 
      0,
     lockKey.category(),
     runDataTestBuilder.loopId,
     runDataTestBuilder.runId,
     runDataTestBuilder.getAndIncrementRunPosition())
    event.addToContext(runDataTestBuilder.loadedInterleaveActionContext)
    position;
  }

  def conditionWaitExit(lockKey : LockKey): Position = {
    val position = new Position(threadIndex, runDataTestBuilder.getAndIncrementRunPosition());
    val event = new ConditionWaitExitEventGen(threadIndex,
      methodCounter,
      lockKey.objectHashCode(),
      0,
      0,
      lockKey.category(),
      runDataTestBuilder.loopId,
      runDataTestBuilder.runId,
      runDataTestBuilder.getAndIncrementRunPosition()
    )
    event.addToContext(runDataTestBuilder.loadedInterleaveActionContext)
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
