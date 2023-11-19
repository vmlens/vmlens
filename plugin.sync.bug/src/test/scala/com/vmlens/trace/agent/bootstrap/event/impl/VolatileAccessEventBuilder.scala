package com.vmlens.trace.agent.bootstrap.event.impl

import com.anarsoft.race.detection.event.gen.VolatileAccessEventGen
import com.vmlens.trace.agent.bootstrap.event.SendEventContext

class VolatileAccessEventBuilder {

  val volatileAccessEvent = new VolatileAccessEvent();

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

  def setProgramCounter(programCounter: Int): VolatileAccessEventBuilder = {
    volatileAccessEvent.setProgramCounter(programCounter)
    this
  }

  def setSlidingWindowId(slidingWindowId: Int): VolatileAccessEventBuilder = {
    volatileAccessEvent.setSlidingWindowId(slidingWindowId)
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

  def setOrder(order: Int): VolatileAccessEventBuilder = {
    volatileAccessEvent.setOrder(order)
    this
  }

  def setThreadId(threadId: Long): VolatileAccessEventBuilder = {
    volatileAccessEvent.setThreadId(threadId)
    this
  }

  def buildJavaEvent() = volatileAccessEvent;


  def buildScalaEvent(): VolatileAccessEventGen = {
    new VolatileAccessEventGen(volatileAccessEvent.threadId(),
      volatileAccessEvent.programCounter(),
      volatileAccessEvent.order(),
      volatileAccessEvent.fieldId(),
      volatileAccessEvent.methodCounter(),
      volatileAccessEvent.slidingWindowId(),
      volatileAccessEvent.methodId(),
      volatileAccessEvent.operation(),
      volatileAccessEvent.objectHashCode(),
      volatileAccessEvent.loopId(),
      volatileAccessEvent.runId(),
      volatileAccessEvent.runPosition()
    );
  }


}
