package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl

import com.anarsoft.race.detection.event.gen.MethodEnterEventGen


class MethodEnterEventBuilder {

  val methodEnterEvent = new MethodEnterEventForTest(6);


  def setThreadIndex(threadIndex: Int): MethodEnterEventBuilder = {
    methodEnterEvent.setThreadIndex(threadIndex)
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

  def buildJavaEvent() = methodEnterEvent;


  def buildScalaEvent(): MethodEnterEventGen = {
    new MethodEnterEventGen(methodEnterEvent.threadIndex(),
      methodEnterEvent.methodId(),
      methodEnterEvent.methodCounter(),
      methodEnterEvent.loopId(),
      methodEnterEvent.runId()
    );
  }

}
