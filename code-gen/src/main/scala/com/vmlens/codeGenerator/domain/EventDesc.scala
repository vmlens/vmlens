package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDescAtomicNonBlocking.{atomicArray, atomicNonBlocking}
import com.vmlens.codeGenerator.domain.EventDescBarrier.barrier
import com.vmlens.codeGenerator.domain.EventDescCondition.{conditionNotify, conditionWait}
import com.vmlens.codeGenerator.domain.EventDescControl.{runStartAndEnd, warning}
import com.vmlens.codeGenerator.domain.EventDescLockOrMonitor.{lock, methodWithLock, monitor}
import com.vmlens.codeGenerator.domain.EventDescMethod.method
import com.vmlens.codeGenerator.domain.EventDescNonVolatileField.{arrayAccess, normalField, staticField}
import com.vmlens.codeGenerator.domain.EventDescThread.{threadJoin, threadStart}
import com.vmlens.codeGenerator.domain.EventDescVolatileField.{volatileField, volatileStaticField}

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, HashSet}

class EventDesc(val name: String,
                val typ: EventTyp,
                val id: Int,
                private val internalFields: ArrayBuffer[FieldDesc],
                val scalaExtends: String ) {

  def typName(): String = typ.name;

  def getByteArraySize(): Int = {
      var size = 0;
      for (f <- javaFields()) {
        size = size + f.typ.size;
      }
      size + 1;
    }

  def javaConstructorOnly() = {
      val result = new ArrayBuffer[FieldDesc]();

      for (f <- internalFields) {
          result.append(f);
      }
      result;
    }

  def javaFields() = {
      val result = new ArrayBuffer[FieldDesc]();
      for (f <- internalFields) {
        result.append(f);
      }
      result;
    }

  def javaFieldsInSend() = {
      val result = new ArrayBuffer[FieldDesc]();
      for (f <- javaFields()) {
        result.append(f);
      }
      result;
    }

  def scalaFields() = {
      val result = new ArrayBuffer[FieldDesc]();
      for (f <- internalFields) {
          result.append(f)
      }
      result;
    }
  
  typ.eventList.append(this);
}

object EventDesc extends GenericDesc {

  val threadIndex = new FieldDesc("threadIndex", intTyp)
  val fieldId = new FieldDesc("fieldId", intTyp)
  val arrayIndex = new FieldDesc("arrayIndex", intTyp)
  val classId = new FieldDesc("classId", intTyp)
  val methodCounter = new FieldDesc("methodCounter", intTyp)
  
  val methodId = new FieldDesc("methodId", intTyp)
  val atomicMethodId = new FieldDesc("atomicMethodId", intTyp)

  
  val objectHashCode = new FieldDesc("objectHashCode", longTyp)
  val monitorId = new FieldDesc("monitorId", intTyp)
  val bytecodePosition = new FieldDesc("bytecodePosition", intTyp)
  val lockType = new FieldDesc("lockType", intTyp)
  val lockKeyCategory = new FieldDesc("lockKeyCategory", intTyp)
  val barrierKeyType = new FieldDesc("barrierKeyType", intTyp)
  val conditionNotifyEventType = new FieldDesc("conditionNotifyEventType", intTyp)
  val startedThreadIndex = new FieldDesc("startedThreadIndex", intTyp)
  val joinedThreadIndex = new FieldDesc("joinedThreadIndex", intTyp)
  val operation = new FieldDesc("operation", intTyp)
  val loopId = new FieldDesc("loopId", intTyp)
  val runId = new FieldDesc("runId", intTyp)
  private val runPosition = new FieldDesc("runPosition", intTyp)
  val messageId = new FieldDesc("messageId", intTyp)
  val eventType  = new FieldDesc("eventType", intTyp)

  
  def plusInterleaveFields(fields: ArrayBuffer[FieldDesc]): ArrayBuffer[FieldDesc] = {
      val clone = fields.clone();
      clone.append(loopId);
      clone.append(runId);
      clone.append(runPosition);
      clone;
    }

  
  def getEventList(eventTypList: ArrayBuffer[EventTyp]): ArrayBuffer[EventDesc] =
    {
      val eventList = new ArrayBuffer[EventDesc]();

      val typField = new EventTyp("NonVolatile", eventTypList, true, "LoadedNonVolatileEvent");
      val typSyncActions = new EventTyp("Interleave", eventTypList, true, "LoadedInterleaveActionEvent");
      val typMethod = new EventTyp("Method", eventTypList, true, "LoadedMethodEvent");
      val typControl = new EventTyp("Control", eventTypList, true, "LoadedControlEvent");

      
      eventList.append(normalField("FieldAccessEventGen"," extends NonVolatileFieldAccessEvent", typField));
      eventList.append(staticField("FieldAccessEventStaticGen", "  extends NonVolatileFieldAccessEventStatic",typField));
      eventList.append(arrayAccess("ArrayAccessEventGen", " extends ArrayAccessEvent ",typField));

      eventList.append(volatileStaticField("VolatileFieldAccessEventStaticGen", " extends VolatileFieldAccessEventStatic  ", typSyncActions));
      eventList.append(volatileField("VolatileFieldAccessEventGen", " extends VolatileFieldAccessEvent  ", typSyncActions));
      
      eventList.append(atomicNonBlocking("AtomicNonBlockingEventGen", " extends AtomicNonBlockingEvent ", typSyncActions));
      
      eventList.append(lock("LockEnterEventGen", " extends LockEnterEvent", typSyncActions));
      eventList.append(lock("LockExitEventGen", " extends LockExitEvent", typSyncActions));

      eventList.append(methodWithLock("AtomicReadWriteLockExitEventGen", " extends AtomicReadWriteLockExitEvent", typSyncActions));
      eventList.append(methodWithLock("AtomicReadWriteLockEnterEventGen", " extends AtomicReadWriteLockEnterEvent", typSyncActions));
      
      eventList.append(monitor("MonitorEnterEventGen", " extends MonitorEnterEvent", typSyncActions));
      eventList.append(monitor("MonitorExitEventGen", " extends MonitorExitEvent", typSyncActions));

      // For Phaser we also need here WaitEnterAndNotify Event
      eventList.append(barrier("BarrierWaitEnterEventGen", " extends BarrierWaitEnterEvent", typSyncActions));
      eventList.append(barrier("BarrierWaitExitEventGen", " extends BarrierWaitExitEvent  ", typSyncActions));
      // Notify works on all related barriers, e.g. similar to notify all
      // probably makes sense to integrate the count for phaser an so on?
      eventList.append(barrier("BarrierNotifyEventGen", " extends BarrierNotifyEvent", typSyncActions));
      
      
      eventList.append(conditionWait("ConditionWaitEnterEventGen", " extends ConditionWaitEnterEvent  ", typSyncActions));
      eventList.append(conditionWait("ConditionWaitExitEventGen", " extends ConditionWaitExitEvent  ", typSyncActions));
      // only used in the report
      eventList.append(conditionNotify("ConditionNotifyEventGen", " extends ConditionNotifyEvent  ", typSyncActions));

      eventList.append(method("MethodEnterEventGen", " extends MethodEnterEvent  ", typMethod));
      eventList.append(method("MethodExitEventGen", " extends MethodExitEvent  ", typMethod));
      
      eventList.append(threadStart("ThreadStartEventGen", " extends ThreadStartEvent  ", typSyncActions));
      eventList.append(threadJoin("ThreadJoinedEventGen", " extends ThreadJoinedEvent  ", typSyncActions));


      eventList.append(runStartAndEnd("RunStartEventGen", " extends RunStartEvent  ",  typControl));
      eventList.append(runStartAndEnd("RunEndEventGen", " extends RunEndEvent  "  , typControl));
      eventList.append(warning("LoopWarningEventGen", " extends LoopWarningEvent  ", typControl));

      eventList;
    }

}