package com.anarsoft.race.detection.inttest

import com.vmlens.trace.agent.bootstrap.lock.LockType
import com.anarsoft.race.detection.event.interleave.{BarrierNotifyEvent, BarrierWaitExitEvent, ConditionNotifyEvent}
import com.vmlens.report.assertion.Position
import com.anarsoft.race.detection.event.gen.{BarrierNotifyEventGen, BarrierWaitExitEventGen, ConditionWaitEnterEventGen, ConditionWaitExitEventGen}
import com.vmlens.trace.agent.bootstrap.barrierkeytype.{BarrierKeyType, BarrierKeyTypeCollection}
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
     position.runPosition())
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
      position.runPosition())
    event.addToContext(runDataTestBuilder.loadedInterleaveActionContext)
    position;
  }

  def conditionNotify(): Position = {
    val position = new Position(threadIndex, runDataTestBuilder.getAndIncrementRunPosition());

    position;
  }

  def barrierNotify(barrierKeyType: BarrierKeyType, objectHashCode: Long): Position = {
    val position = new Position(threadIndex, runDataTestBuilder.getAndIncrementRunPosition());
    val barrierEvent = new BarrierNotifyEventGen(threadIndex,
      methodCounter,
      objectHashCode,
      BarrierKeyTypeCollection.SINGLETON.toId(barrierKeyType),
      0,
      0,
      runDataTestBuilder.loopId,
      runDataTestBuilder.runId,
      position.runPosition())
    barrierEvent.addToContext(runDataTestBuilder.loadedInterleaveActionContext)

    position;
  }

  def barrierWaitExit(barrierKeyType: BarrierKeyType, objectHashCode: Long): Position = {
    val position = new Position(threadIndex, runDataTestBuilder.getAndIncrementRunPosition());
    val barrierEvent = new BarrierWaitExitEventGen(threadIndex,
      methodCounter,
      objectHashCode,
      BarrierKeyTypeCollection.SINGLETON.toId(barrierKeyType),
      0,
      0,
      runDataTestBuilder.loopId,
      runDataTestBuilder.runId,
      position.runPosition())
    barrierEvent.addToContext(runDataTestBuilder.loadedInterleaveActionContext)

    position;
  }

  def getAndIncrementMethodCounter(): Int = {
    val temp = methodCounter;
    methodCounter = methodCounter + 1;
    temp;
  }

}
