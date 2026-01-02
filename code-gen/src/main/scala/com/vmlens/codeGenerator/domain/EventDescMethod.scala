package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDesc.{loopId, methodCounter, methodId, nextId, plusInterleaveFields, runId, threadIndex, dominatorTreeCounter}
import com.vmlens.codeGenerator.domain.EventDescLockOrMonitor.fieldListMonitor

import scala.collection.mutable.ArrayBuffer


object EventDescMethod {

  private def fieldListMethodEnter() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(methodId);
    fields.append(methodCounter);
    fields.append(loopId);
    fields.append(runId);
    fields.append(dominatorTreeCounter);
    fields;
  }

  private def fieldListMethodExit() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(methodCounter);
    fields.append(loopId);
    fields.append(runId);
    fields.append(dominatorTreeCounter);
    fields;
  }

  def methodEnter(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), fieldListMethodEnter(), extendsString)

  def methodExit(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), fieldListMethodExit(), extendsString)


}
