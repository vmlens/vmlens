package com.vmlens.trace.agent.bootstrap.event.impl

import com.anarsoft.race.detection.event.gen.MethodEnterEventGen


class MethodEnterEventBuilder {

  val methodEnterEvent = new MethodEnterEvent();


  def setThreadIndex(threadIndex: Int): MethodEnterEventBuilder = {
    methodEnterEvent.setThreadIndex(threadIndex)
    this
  }

  def setMethodId(methodId: Int): MethodEnterEventBuilder = {
    methodEnterEvent.setMethodId(methodId)
    this
  }

  def setMethodCounter(methodCounter: Int): MethodEnterEventBuilder = {
    methodEnterEvent.setMethodCounter(methodCounter)
    this
  }

  def setLoopId(loopId: Int): MethodEnterEventBuilder = {
    methodEnterEvent.setLoopId(loopId)
    this
  }

  def setRunId(runId: Int): MethodEnterEventBuilder = {
    methodEnterEvent.setRunId(runId)
    this
  }

  def setRunPosition(runPosition: Int): MethodEnterEventBuilder = {
    methodEnterEvent.setRunPosition(runPosition)
    this
  }

  def buildJavaEvent() = methodEnterEvent;


  def buildScalaEvent(): MethodEnterEventGen = {
    new MethodEnterEventGen(methodEnterEvent.threadIndex(),
      methodEnterEvent.methodId(),
      methodEnterEvent.methodCounter(),
      methodEnterEvent.loopId(),
      methodEnterEvent.runId(),
      methodEnterEvent.runPosition()
    );
  }

}
