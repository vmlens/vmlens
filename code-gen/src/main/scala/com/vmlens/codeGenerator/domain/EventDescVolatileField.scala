package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDesc.{bytecodePosition, fieldId, methodCounter, methodId, nextId, objectHashCode, operation, plusInterleaveFields, threadIndex}

import scala.collection.mutable.ArrayBuffer

object EventDescVolatileField {

  private def fieldListVolatileAccessBasic() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(bytecodePosition);
    fields.append(fieldId);
    fields.append(methodCounter);
    fields;
  }
  
  private def fieldListVolatileAccessStatic() = {
    val fields = fieldListVolatileAccessBasic();
    fields.append(methodId);
    fields.append(operation);
    fields;
  }

  private def fieldListVolatileAccessObject() = {
    val fields = fieldListVolatileAccessBasic();
    fields.append(methodId);
    fields.append(operation);
    fields.append(objectHashCode);
    fields;
  }
  
  
  def volatileField(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), plusInterleaveFields(fieldListVolatileAccessObject()), extendsString)

  def volatileStaticField(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), plusInterleaveFields(fieldListVolatileAccessStatic()), extendsString)

}
