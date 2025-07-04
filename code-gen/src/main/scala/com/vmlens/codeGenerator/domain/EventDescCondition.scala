package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDesc.*

import scala.collection.mutable.ArrayBuffer

object EventDescCondition {

  private def fieldListCondition() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(methodCounter);
    fields.append(objectHashCode);
    fields.append(bytecodePosition);
    fields.append(methodId);
    fields;
  }
  
  private def fieldListWait() = {
    val fields = fieldListCondition();
    fields.append(lockKeyCategory)
    fields;
  }

  private def fieldListNotify() = {
    val fields = fieldListCondition();
    fields.append(conditionNotifyEventType)
    fields;
  }


  def conditionWait(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), plusInterleaveFields(fieldListWait()), extendsString)

  def conditionNotify(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), plusInterleaveFields(fieldListNotify()), extendsString)

}
