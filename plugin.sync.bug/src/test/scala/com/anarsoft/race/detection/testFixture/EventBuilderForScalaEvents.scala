package com.anarsoft.race.detection.testFixture

import com.anarsoft.race.detection.event.gen.VolatileAccessEventGen
import com.anarsoft.race.detection.event.syncAction.LoadedSyncActionEvent
import com.vmlens.trace.agent.bootstrap.testFixture.EventBuilder

import scala.collection.mutable.ArrayBuffer

class EventBuilderForScalaEvents extends EventBuilder {

  val syncActionEvents = new ArrayBuffer[LoadedSyncActionEvent]();


  override def addVolatileAccessEvent(threadId: Long, order: Int, fieldId: Int, methodId: Int,
                                      operation: Int, objectHashCode: Long, runId: Int, loopId: Int,
                                      slidingWindowId: Int): Unit = {
    syncActionEvents.append(new VolatileAccessEventGen(threadId, 0,
      order, fieldId, 0, 0, 0, methodId, operation, objectHashCode, loopId,
      runId, 0));
  }
}
