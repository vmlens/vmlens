package com.anarsoft.race.detection.testFixture

import com.anarsoft.race.detection.event.gen.VolatileFieldAccessEventGen
import com.anarsoft.race.detection.event.method.LoadedMethodEvent
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.{MethodEnterEventBuilder, VolatileAccessEventBuilder}

import scala.collection.mutable.ArrayBuffer

class EventBuilder(val runId: Int, val loopId: Int, val slidingWindowId: Int) {

  val javaSyncActions = new ArrayBuffer[RuntimeEvent]();
  val volatileAccessEvents = new ArrayBuffer[VolatileFieldAccessEventGen]();

  val methodJavaEvents = new ArrayBuffer[RuntimeEvent]();
  val methodEvents = new ArrayBuffer[LoadedMethodEvent]();

  var runPosition = 0;
  var methodCounter = 0;


  def add(volatileAccessEvent: VolatileAccessEventBuilder): Unit = {
    volatileAccessEvent.setRunId(runId);
    volatileAccessEvent.setLoopId(loopId);
    volatileAccessEvent.setRunPosition(runPosition)

    volatileAccessEvent.setMethodCounter(methodCounter)

    runPosition = runPosition + 1;
    volatileAccessEvents += volatileAccessEvent.buildScalaEvent();
    javaSyncActions += volatileAccessEvent.buildJavaEvent();
  }

  def add(methodEnterEvent: MethodEnterEventBuilder): Unit = {
    methodEnterEvent.setRunId(runId);
    methodEnterEvent.setLoopId(loopId);

    methodCounter = methodCounter + 1;

    methodEnterEvent.setMethodCounter(methodCounter)
    runPosition = runPosition + 1;
    methodEvents += methodEnterEvent.buildScalaEvent();
    methodJavaEvents += methodEnterEvent.buildJavaEvent();
  }


  def build(): TestData = {
    TestData(javaSyncActions.toList, volatileAccessEvents.toList, methodJavaEvents.toList, methodEvents.toList);
  }


}
