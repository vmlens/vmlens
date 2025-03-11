package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl

import com.anarsoft.race.detection.event.gen.VolatileAccessEventGen

class VolatileAccessEventBuilder {

  val volatileAccessEvent = new VolatileAccessEventForTest();

  def setRunPosition(runPosition: Int): VolatileAccessEventBuilder = {
    volatileAccessEvent.setRunPosition(runPosition)
    this;
  }

  def setRunId(runId: Int): VolatileAccessEventBuilder = {
    volatileAccessEvent.setRunId(runId)
    this
  }

  def setLoopId(loopId: Int): VolatileAccessEventBuilder = {
    volatileAccessEvent.setLoopId(loopId)
    this
  }

  def setMethodCounter(methodCounter: Int): VolatileAccessEventBuilder = {
    volatileAccessEvent.setMethodCounter(methodCounter)
    this
  }


  def setObjectHashCode(objectHashCode: Long): VolatileAccessEventBuilder = {
    volatileAccessEvent.setObjectHashCode(objectHashCode)
    this
  }

  def setOperation(operation: Int): VolatileAccessEventBuilder = {
    volatileAccessEvent.setOperation(operation)
    this
  }

  def setMethodId(methodId: Int): VolatileAccessEventBuilder = {
    volatileAccessEvent.setMethodId(methodId)
    this
  }

  def setFieldId(fieldId: Int): VolatileAccessEventBuilder = {
    volatileAccessEvent.setFieldId(fieldId)
    this
  }


  def setThreadIndex(threadIndex: Int): VolatileAccessEventBuilder = {
    volatileAccessEvent.setThreadIndex(threadIndex)
    this
  }

  def buildJavaEvent() = volatileAccessEvent;


  def buildScalaEvent(): VolatileAccessEventGen = {
    new VolatileAccessEventGen(volatileAccessEvent.threadIndex(),
      volatileAccessEvent.bytecodePosition(),
      volatileAccessEvent.fieldId(),
      volatileAccessEvent.methodCounter(),
      volatileAccessEvent.methodId(),
      volatileAccessEvent.operation(),
      volatileAccessEvent.objectHashCode(),
      volatileAccessEvent.loopId(),
      volatileAccessEvent.runId(),
      volatileAccessEvent.runPosition()
    );
  }


}
