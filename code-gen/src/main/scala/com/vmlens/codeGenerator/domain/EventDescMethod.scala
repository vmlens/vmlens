package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDesc.{loopId, methodCounter, methodId, nextId, plusInterleaveFields, runId, threadIndex}
import com.vmlens.codeGenerator.domain.EventDescLockOrMonitor.fieldListMonitor

import scala.collection.mutable.ArrayBuffer


object EventDescMethod {

  private def fieldListMethod() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(methodId);
    fields.append(methodCounter);
    fields.append(loopId);
    fields.append(runId);
    fields;
  }

  def method(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), fieldListMethod(), extendsString)


}
