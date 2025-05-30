package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDesc.{loopId, messageId, nextId, runId}

import scala.collection.mutable.ArrayBuffer

object EventDescControl {

  private def runField() = {
    val fields = new ArrayBuffer[FieldDesc];
    fields.append(loopId);
    fields.append(runId);

    fields;
  }

  private def plusMessageFields(fields: ArrayBuffer[FieldDesc]) = {
    val clone = fields.clone();
    clone.append(messageId);
    clone;
  }
  
  def runStartAndEnd(eventName : String, extendsString : String, typControl : EventTyp) : EventDesc  = 
    new EventDesc(eventName, typControl, nextId(),runField(), extendsString, false, false,None)
  
  def warning(eventName : String, extendsString : String, typControl : EventTyp) : EventDesc  =
    new EventDesc(eventName, typControl, nextId(),plusMessageFields(runField()), extendsString, false, false,None)
  
}
