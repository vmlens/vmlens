package com.anarsoft.race.detection.testFixture

import com.vmlens.trace.agent.bootstrap.MemoryAccessType
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.{MethodEnterEventBuilder, VolatileAccessEventBuilder}

import scala.collection.mutable.Stack

class EventBuilderForThread(private val threadIndex: Int, private val eventBuilder: EventBuilder) {

  private val methodStack = new Stack[Int]();
  private var programCounter = 0;

  def addMethodEnterEvent(methodId: Int): Unit = {
    methodStack.push(methodId);

    val methodEnterEventBuilder = new MethodEnterEventBuilder();
    methodEnterEventBuilder.setThreadIndex(threadIndex);
    eventBuilder.add(methodEnterEventBuilder)
  }


  def addVolatileRead(field: EventBuilderForSyncAction): Unit = {
    addVolatileField(MemoryAccessType.IS_READ, field);
  }

  def addVolatileWrite(field: EventBuilderForSyncAction): Unit = {
    addVolatileField(MemoryAccessType.IS_WRITE, field);
  }


  private def addVolatileField(operation: Int, field: EventBuilderForSyncAction): Unit = {
    val event = new VolatileAccessEventBuilder();
    event.setOperation(operation);

    if (!methodStack.isEmpty) {
      event.setMethodId(methodStack.top);
    }

    field.setFieldValues(event);
    eventBuilder.add(event)
    programCounter = programCounter + 1;
  }

}
