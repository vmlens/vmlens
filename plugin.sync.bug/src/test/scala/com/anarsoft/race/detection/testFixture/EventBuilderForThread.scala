package com.anarsoft.race.detection.testFixture

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType
import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEventBuilder

class EventBuilderForThread(val threadId: Long) {

  var programCounter = 0;

  var methodCounter = 0;
  var methodId = 0;


  def addVolatileRead(field: EventBuilderForSyncAction): Unit = {
    addVolatileField(MemoryAccessType.IS_READ, field);
  }

  def addVolatileWrite(field: EventBuilderForSyncAction): Unit = {
    addVolatileField(MemoryAccessType.IS_WRITE, field);
  }


  private def addVolatileField(operation: Int, field: EventBuilderForSyncAction): Unit = {
    val event = new VolatileAccessEventBuilder();
    event.setProgramCounter(programCounter);
    event.setOperation(operation);
    event.setMethodId(methodId);
    event.setMethodCounter(methodCounter);
    field.add(event);
    programCounter = programCounter + 1;
  }

}
