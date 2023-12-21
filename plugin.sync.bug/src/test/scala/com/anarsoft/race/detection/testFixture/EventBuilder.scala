package com.anarsoft.race.detection.testFixture

import com.anarsoft.race.detection.event.gen.VolatileAccessEventGen
import com.vmlens.trace.agent.bootstrap.event.StaticEvent
import com.vmlens.trace.agent.bootstrap.event.impl.{VolatileAccessEvent, VolatileAccessEventBuilder}

import java.util.ArrayList
import scala.collection.mutable.ArrayBuffer

class EventBuilder(val runId: Int, val loopId: Int) {

  val javaSyncActions = new ArrayBuffer[StaticEvent]();
  val volatileAccessEvents = new ArrayList[VolatileAccessEventGen]();

  var runPosition = 0;


  def add(volatileAccessEvent: VolatileAccessEventBuilder): Unit = {
    volatileAccessEvent.setRunId(runId);
    volatileAccessEvent.setLoopId(loopId);

    volatileAccessEvent.setRunPosition(runPosition)
    runPosition = runPosition + 1;
    volatileAccessEvents.add(volatileAccessEvent.buildScalaEvent());
    javaSyncActions += volatileAccessEvent.buildJavaEvent();
  }


  def build(): TestData = {
    TestData(javaSyncActions.toSeq, volatileAccessEvents);
  }


}
