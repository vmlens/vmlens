package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDesc.*

import scala.collection.mutable.ArrayBuffer

object EventDescCondition {

  private def fieldListLockOrMonitor() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(methodCounter);
    fields.append(objectHashCode);
    fields.append(conditionType)
    fields.append(bytecodePosition);
    fields.append(methodId);
    fields;
  }


  def condition(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), plusInterleaveFields(fieldListLockOrMonitor()), extendsString)


}
