package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDesc.{arrayIndex, bytecodePosition, atomicMethodId, lockType, methodCounter, methodId, nextId, objectHashCode, operation, plusInterleaveFields, threadIndex}

import scala.collection.mutable.ArrayBuffer

object EventDescLockOrMonitor {

  private def fieldListLockOrMonitor() = {
    val fields = new ArrayBuffer[FieldDesc]();
    fields.append(threadIndex);
    fields.append(methodCounter);
    fields.append(objectHashCode);
    fields;
  }
  
  private def fieldListLock() = {
    val fields = fieldListLockOrMonitor();
    fields.append(lockType)
    fields.append(bytecodePosition);
    fields.append(methodId);
    fields;
  }

  private def fieldListMonitor() = {
    val fields = fieldListLockOrMonitor();
    fields.append(methodId);
    fields.append(bytecodePosition);
    fields;
  }

  def plusAtomicMethodId(fields: ArrayBuffer[FieldDesc]) = {

    val clone = fields.clone();
    clone.append(atomicMethodId);
    clone;
  }

  def monitor(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), plusInterleaveFields(fieldListMonitor()), extendsString)

  def lock(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), plusInterleaveFields(fieldListLock()), extendsString)

  def methodWithLock(eventName: String, extendsString: String, typControl: EventTyp): EventDesc =
    new EventDesc(eventName, typControl, nextId(), plusAtomicMethodId(plusInterleaveFields(fieldListLock())), extendsString)


}
