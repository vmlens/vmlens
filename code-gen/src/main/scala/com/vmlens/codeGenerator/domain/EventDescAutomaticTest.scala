package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDesc.{plusInterleaveFields, automaticTestType, bytecodePosition, nextId, methodCounter, methodId, automaticTestId, objectHashCode, automaticTestMethodId, threadIndex, loopId, runId}

import scala.collection.mutable.ArrayBuffer

object EventDescAutomaticTest {

  private def fieldListAutomaticTestMethod() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(automaticTestType);
    fields.append(automaticTestId);
    fields.append(automaticTestMethodId);
    fields.append(loopId);
    fields.append(runId);
    fields;
  }

  private def fieldListAutomaticTestSuccess() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(automaticTestId);
    fields.append(loopId);
    fields.append(runId);
    fields;
  }


  def automaticTestMethod(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), fieldListAutomaticTestMethod(), extendsString)

  def automaticTestSuccess(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), fieldListAutomaticTestSuccess() , extendsString)


}
