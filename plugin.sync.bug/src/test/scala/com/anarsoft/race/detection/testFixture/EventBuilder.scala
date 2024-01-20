package com.anarsoft.race.detection.testFixture

import com.anarsoft.race.detection.event.gen.VolatileAccessEventGen
import com.anarsoft.race.detection.event.method.LoadedMethodEvent
import com.vmlens.trace.agent.bootstrap.event.impl.{MethodEnterEventBuilder, VolatileAccessEvent, VolatileAccessEventBuilder}
import com.vmlens.trace.agent.bootstrap.event.{RuntimeEvent, StaticEvent}

import scala.collection.mutable.ArrayBuffer

class EventBuilder(val runId: Int, val loopId: Int, val slidingWindowId: Int) {

  val javaSyncActions = new ArrayBuffer[StaticEvent]();
  val volatileAccessEvents = new ArrayBuffer[VolatileAccessEventGen]();

  val methodJavaEvents = new ArrayBuffer[RuntimeEvent]();
  val methodEvents = new ArrayBuffer[LoadedMethodEvent]();

  var runPosition = 0;
  var methodCounter = 0;


  def add(volatileAccessEvent: VolatileAccessEventBuilder): Unit = {
    volatileAccessEvent.setRunId(runId);
    volatileAccessEvent.setLoopId(loopId);
    volatileAccessEvent.setSlidingWindowId(slidingWindowId);
    volatileAccessEvent.setRunPosition(runPosition)

    volatileAccessEvent.setMethodCounter(methodCounter)

    runPosition = runPosition + 1;
    volatileAccessEvents += volatileAccessEvent.buildScalaEvent();
    javaSyncActions += volatileAccessEvent.buildJavaEvent();
  }

  def add(methodEnterEvent: MethodEnterEventBuilder): Unit = {
    methodEnterEvent.setRunId(runId);
    methodEnterEvent.setLoopId(loopId);
    methodEnterEvent.setSlidingWindowId(slidingWindowId);

    methodCounter = methodCounter + 1;

    methodEnterEvent.setMethodCounter(methodCounter)
    methodEnterEvent.setRunPosition(runPosition)
    runPosition = runPosition + 1;
    methodEvents += methodEnterEvent.buildScalaEvent();
    methodJavaEvents += methodEnterEvent.buildJavaEvent();
  }


  def build(): TestData = {
    TestData(javaSyncActions.toList, volatileAccessEvents.toList, methodJavaEvents.toList, methodEvents.toList);
  }


}
