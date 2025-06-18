package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDesc.{atomicMethodId, bytecodePosition, eventType, joinedThreadIndex, lockType, methodCounter, methodId, nextId, objectHashCode, operation, plusInterleaveFields, startedThreadIndex, threadIndex}
import com.vmlens.codeGenerator.domain.EventDescNonVolatileField.fieldListObjectAccess

import scala.collection.mutable.ArrayBuffer

object EventDescThread {

  private def fieldListThreadStart() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(bytecodePosition);
    fields.append(methodId);
    fields.append(startedThreadIndex);
    fields.append(methodCounter);
    fields.append(eventType);
    fields;
  }

  private def fieldListThreadJoin() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(bytecodePosition);
    fields.append(methodId);
    fields.append(joinedThreadIndex);
    fields.append(methodCounter);
    fields.append(eventType);
    fields;
  }


  def threadStart(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(),  plusInterleaveFields(fieldListThreadStart()), extendsString)

  def threadJoin(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(),  plusInterleaveFields(fieldListThreadJoin()), extendsString)


}
