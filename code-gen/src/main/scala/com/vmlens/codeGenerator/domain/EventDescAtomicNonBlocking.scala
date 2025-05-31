package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDesc.{bytecodePosition, atomicMethodId, arrayIndex, fieldId, methodCounter, methodId, nextId, objectHashCode, operation, plusInterleaveFields, threadIndex}

import scala.collection.mutable.ArrayBuffer
import com.vmlens.codeGenerator.domain.EventDesc.{nextId, plusInterleaveFields}
import com.vmlens.codeGenerator.domain.EventDescNonVolatileField.fieldListObjectAccess

object EventDescAtomicNonBlocking {

  private def fieldListNonBlocking() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(bytecodePosition);
    fields.append(methodCounter);
    fields.append(methodId);
    fields.append(operation);
    fields.append(objectHashCode);
    fields.append(atomicMethodId);
    fields;
  }

  private def fieldListVolatileAccessArray() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(bytecodePosition);
    fields.append(arrayIndex);
    fields.append(methodCounter);
    fields.append(methodId);
    fields.append(operation);
    fields.append(objectHashCode);
    fields;
  }

  def atomicNonBlocking(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), plusInterleaveFields(fieldListNonBlocking()), extendsString)

  def atomicArray(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(),  plusInterleaveFields(fieldListVolatileAccessArray()), extendsString)
  
}
