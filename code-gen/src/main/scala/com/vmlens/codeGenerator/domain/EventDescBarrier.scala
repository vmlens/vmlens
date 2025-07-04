package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDesc.{bytecodePosition, barrierKeyType, methodCounter, methodId, nextId, objectHashCode, plusInterleaveFields, threadIndex}

import scala.collection.mutable.ArrayBuffer

object EventDescBarrier {

  private def fieldListLockOrMonitor() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(methodCounter);
    fields.append(objectHashCode);
    fields.append(barrierKeyType)
    fields.append(bytecodePosition);
    fields.append(methodId);
    fields;
  }


  def barrier(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), plusInterleaveFields(fieldListLockOrMonitor()), extendsString)


}
