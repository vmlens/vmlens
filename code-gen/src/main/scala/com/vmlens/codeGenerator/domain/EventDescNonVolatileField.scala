package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDesc.{arrayIndex, fieldId, methodCounter, methodId, nextId, objectHashCode, operation, plusInterleaveFields, threadIndex}

import scala.collection.mutable.ArrayBuffer

object EventDescNonVolatileField {

  private def fieldListStaticAccess() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(fieldId);
    fields.append(methodCounter);
    fields.append(operation);
    fields.append(methodId);
    fields;
  }

  private def fieldListObjectAccess() = {
    val fields = fieldListStaticAccess();
    fields.append(objectHashCode);
    fields;
  }

  private def fieldListArrayAccess() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(arrayIndex);
    fields.append(methodCounter);
    fields.append(objectHashCode);
    fields.append(operation);
    fields.append(methodId);
    fields;
  }
  
  def normalField(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(),  plusInterleaveFields(fieldListObjectAccess()), extendsString)

  def staticField(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), plusInterleaveFields(fieldListStaticAccess()), extendsString)

  def arrayAccess(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), plusInterleaveFields(fieldListArrayAccess()), extendsString)


}
