package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.domain.EventDescControl.{runStartAndEnd, warning}

import scala.collection.mutable.{ArrayBuffer, HashSet}

class EventDesc(val name: String, val typ: EventTyp, val id: Int, private val internalFields: ArrayBuffer[FieldDesc],
                val scalaExtends: String, val hasInterleaveEvent: Boolean, val isInterleaveEvent: Boolean,val additionalInterleaveFlag : Option[String] ) {

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
        if (f.occurence.isInJavaEvent() && !f.occurence.isInScalaEvent()) {
          result.append(f);
        }
      }
      result;
    }

  def javaFields() = {
      val result = new ArrayBuffer[FieldDesc]();
      for (f <- internalFields) {
        if (f.occurence.isInJavaEvent()) {
          result.append(f);
        }
      }
      result;
    }

  def javaFieldsInSend() = {
      val result = new ArrayBuffer[FieldDesc]();
      for (f <- javaFields()) {
        if (f.nameForSend.isEmpty) {
          result.append(f);
        }
      }
      result;
    }

  def scalaFields() = {
      val result = new ArrayBuffer[FieldDesc]();
      for (f <- internalFields) {
        if (f.occurence.isInScalaEvent()) {
          result.append(f);
        }
      }
      result;
    }
  
  typ.eventList.append(this);
}

object EventDesc extends GenericDesc {

  val threadIndex = new FieldDesc("threadIndex", intTyp, All(), Some("threadIndex()"))

  
  val hasCallback = new FieldDesc("hasCallback", byteTyp, All(), None)


  val fieldId = new FieldDesc("fieldId", intTyp, All(), None)
  val arrayIndex = new FieldDesc("arrayIndex", intTyp, All(), None)

 // val index = new FieldDesc("index", longTyp, All(), None)


  val classId = new FieldDesc("classId", intTyp, All(), None)
  val methodCounter = new FieldDesc("methodCounter", intTyp, All(), None)

  val parallizeId = new FieldDesc("parallizeId", intTyp, All(), None)
  
  val isWrite = new FieldDesc("isWrite", booleanTyp, All(), None)
  val methodId = new FieldDesc("methodId", intTyp, All(), None)

  val atomicMethodId = new FieldDesc("atomicMethodId", intTyp, All(), None)

  val stampedLockMethodId = new FieldDesc("stampedLockMethodId", intTyp, All(), None)
  
  val objectHashCode = new FieldDesc("objectHashCode", longTyp, All(), None)
  val monitorId = new FieldDesc("monitorId", intTyp, All(), None)
  val bytecodePosition = new FieldDesc("bytecodePosition", intTyp, All(), None)
  val isShared = new FieldDesc("isShared", booleanTyp, All(), None)
  val lockType = new FieldDesc("lockType", intTyp, All(), None)
  val startedThreadIndex = new FieldDesc("startedThreadIndex", intTyp, All(), None)
  val joinedThreadIndex = new FieldDesc("joinedThreadIndex", intTyp, All(), None)
  val operation = new FieldDesc("operation", intTyp, All(), None)
  val threadIdAtEvent = new FieldDesc("threadIdAtEvent", longTyp, All(), None)
  val loopId = new FieldDesc("loopId", intTyp, All(), None)
  val runId = new FieldDesc("runId", intTyp, All(), None)
  val runPosition = new FieldDesc("runPosition", intTyp, All(), None)
  val messageId = new FieldDesc("messageId", intTyp, All(), None)

  
  def fieldListStaticAccess() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);

      fields.append(fieldId);
      fields.append(methodCounter);
      fields.append(operation);
      fields.append(methodId);

      fields;

    }

  def fieldListVolatileAccessBasic() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);

      fields.append(bytecodePosition);
      fields.append(fieldId);
      fields.append(methodCounter);



      fields;
    }

  def fieldListMethod() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);

      fields.append(methodId);
      fields.append(methodCounter);

      fields.append(loopId);
      fields.append(runId);

      fields;
    }

  def fieldListMethodAtomic() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);

      fields.append(methodId);
      fields.append(methodCounter);

       fields.append(hasCallback);


      plusInterleaveFields(fields);
    }

  def fieldListMethodCallback() =
    {
      val fields = new ArrayBuffer[FieldDesc]();
      fields.append(threadIndex);

      fields.append(methodCounter);

      plusInterleaveFields(fields);
    }






  def fieldListLockOrMonitor() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);
      fields.append(methodCounter);
      fields.append(objectHashCode);

      fields;
    }

  def fieldListTransfer() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);


      fields.append(monitorId);

      fields;
    }

  def fieldListThreadBegin() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);

      fields.append(bytecodePosition);
      fields.append(methodId);

      fields.append(startedThreadIndex);
      fields.append(methodCounter);

      fields;
    }

  def fieldListThreadJoin() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);


      fields.append(bytecodePosition);
      fields.append(methodId);


      fields.append(joinedThreadIndex);
      fields.append(methodCounter);

      fields;
    }

  def fieldListLock() =
    {
      val fields = fieldListLockOrMonitor();

      fields.append(lockType)
      fields.append(bytecodePosition);
      fields.append(methodId);

      fields;

    }



  def fieldListMonitorEnter() =
    {
      val fields = fieldListLockOrMonitor();

      fields.append(methodId);
      fields.append(bytecodePosition);

      fields;

    }
  
  def fieldListMethodCoverage() =
    {
      val fields = fieldListMethod()

      fields;
    }

  def fieldListParallizedMethodEnter() =
    {
      val fields = fieldListMethod()
      fields.append(parallizeId);

      fields;
    }



  def stateEvent() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);
      fields.append(classId);

      fields.append(methodId);
      fields.append(methodCounter);
      fields.append(operation);

      fields.append(objectHashCode);

      fields;
    }

  def stateEventInitial() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIdAtEvent);
      fields.append(classId);

      fields.append(methodId);
      fields.append(methodCounter);
      fields.append(operation);

      fields.append(objectHashCode);


      fields;
    }

  def stateArrayEvent() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);

      fields.append(methodId);
      fields.append(bytecodePosition);

      fields.append(methodCounter);
      fields.append(operation);
      fields.append(objectHashCode);

      fields.append(classId);


      fields;
    }



  def stateStaticEvent() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);
      fields.append(fieldId);

      fields.append(methodId);
      fields.append(methodCounter);
      fields.append(operation);

      fields;
    }

  def stateEventStaticInitial() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIdAtEvent);
      fields.append(fieldId);

      fields.append(methodId);
      fields.append(methodCounter);
      fields.append(operation);


      fields;
    }

  def fieldListVolatileAccessStatic() =
    {
      val fields = fieldListVolatileAccessBasic();
      fields.append(methodId);
      fields.append(operation);


      fields;
    }

  def fieldListVolatileAccessObject() =
    {
      val fields = fieldListVolatileAccessBasic();
      fields.append(methodId);
      fields.append(operation);

      fields.append(objectHashCode);

      fields;
    }

  def fieldListNonBlocking() =
  {
    val fields = new ArrayBuffer[FieldDesc]();

    fields.append(threadIndex);

    fields.append(bytecodePosition);
    fields.append(methodCounter);
    fields.append(methodId);
    fields.append(operation);

    fields.append(objectHashCode);

    fields;
  }

  def fieldListVolatileAccessArray() =
    {
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

  def fieldListObjectAccess() =
    {
      val fields = fieldListStaticAccess();
      fields.append(objectHashCode);
      fields;
    }

  def fieldListArrayAccess() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);
      fields.append(arrayIndex);
      fields.append(methodCounter);
      fields.append(objectHashCode);
      fields.append(operation);
      fields.append(methodId);


      fields;
    }

  def fieldListStackTrace() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);
      fields;
    }

  def fieldListDirectMemoryBasic() =
    {
      val fields = new ArrayBuffer[FieldDesc]();

      fields.append(threadIndex);
      fields.append(methodCounter);

      fields.append(objectHashCode);


      fields;
    }

  def fieldListDirectMemoryVolatile() =
    {
      val fields = fieldListDirectMemoryBasic();
      fields.append(operation);
      fields;

    }

  def plusInterleaveFields(fields: ArrayBuffer[FieldDesc]) =
    {

      val clone = fields.clone();

      clone.append(loopId);
      clone.append(runId);
      clone.append(runPosition);
      clone;
    }


  def plusAtonicMethodId(fields: ArrayBuffer[FieldDesc]) =
  {

    val clone = fields.clone();
    clone.append(atomicMethodId);

    clone;
  }

   def plusInterleaveFields4SharedMemory(fields: ArrayBuffer[FieldDesc]) =
    {

      val clone = fields.clone();
      clone.append(loopId);
      clone.append(runId);
      clone.append(runPosition);
      clone;
    }



  def interleaveFields() = plusInterleaveFields(new ArrayBuffer[FieldDesc]);
  
  def getEventList(eventTypList: ArrayBuffer[EventTyp]) =
    {
      val eventList = new ArrayBuffer[EventDesc]();

      val typField = new EventTyp("NonVolatile", eventTypList, true, "LoadedNonVolatileEvent");
      val typSyncActions = new EventTyp("Interleave", eventTypList, true, "LoadedInterleaveActionEvent");
      val typMethod = new EventTyp("Method", eventTypList, true, "LoadedMethodEvent");
      val typControl = new EventTyp("Control", eventTypList, true, "LoadedControlEvent");


      val fieldAccessId = nextId();



      val fieldListObject = fieldListObjectAccess();


      eventList.append(new EventDesc("FieldAccessEventGen", typField, nextId(), plusInterleaveFields4SharedMemory(fieldListObject), " extends NonVolatileFieldAccessEvent", false, true,None));
      
      val fieldListStatic = fieldListStaticAccess();


      val fieldListArray = fieldListArrayAccess();

      eventList.append(new EventDesc("FieldAccessEventStaticGen", typField, nextId(), plusInterleaveFields4SharedMemory(fieldListStatic), " extends NonVolatileFieldAccessEventStatic", false, true,None));

      eventList.append(new EventDesc("ArrayAccessEventGen", typField, nextId(), plusInterleaveFields4SharedMemory(fieldListArray), " extends ArrayAccessEvent ", false, true,None));

      eventList.append(new EventDesc("VolatileFieldAccessEventStaticGen", typSyncActions, nextId(), plusInterleaveFields(fieldListVolatileAccessStatic()), " extends VolatileFieldAccessEventStatic  ", false, true,None));

      eventList.append(new EventDesc("VolatileFieldAccessEventGen", typSyncActions, nextId(), plusInterleaveFields(fieldListVolatileAccessObject()), " extends VolatileFieldAccessEvent ", false, true,None));

      eventList.append(new EventDesc("VolatileArrayAccessEventGen", typSyncActions, nextId(), plusInterleaveFields(fieldListVolatileAccessArray()), " extends VolatileArrayAccessEvent", false, true,None));
      
      eventList.append(new EventDesc("LockEnterEventGen", typSyncActions, nextId(), plusInterleaveFields(fieldListLock()), " extends LockEnterEvent  " , false, true,None));

      eventList.append(new EventDesc("LockExitEventGen", typSyncActions, nextId(), plusInterleaveFields(fieldListLock()), " extends LockExitEvent", false, true,None));

      eventList.append(new EventDesc("MonitorEnterEventGen", typSyncActions, nextId(), plusInterleaveFields(fieldListMonitorEnter()), " extends MonitorEnterEvent", false, true,None));
      eventList.append(new EventDesc("MonitorExitEventGen", typSyncActions, nextId(), plusInterleaveFields(fieldListMonitorEnter()), " extends MonitorExitEvent", false, true,None));

      eventList.append(new EventDesc("MethodEnterEventGen", typMethod, nextId(), fieldListMethod(), " extends MethodEnterEvent  ", false, false,None));
      eventList.append(new EventDesc("MethodExitEventGen", typMethod, nextId(),  fieldListMethod(), " extends MethodExitEvent ", false, false,None));

      eventList.append(new EventDesc("ThreadStartEventGen", typSyncActions, nextId(), plusInterleaveFields(fieldListThreadBegin()), " extends ThreadStartEvent ", false, false,None));
      eventList.append(new EventDesc("ThreadJoinedEventGen", typSyncActions, nextId(), plusInterleaveFields(fieldListThreadJoin()), " extends ThreadJoinedEvent  ", false, false,None));


      eventList.append(new EventDesc("AtomicReadWriteLockEnterEventGen", typSyncActions, nextId(), plusAtonicMethodId(plusInterleaveFields(fieldListLock())), " extends AtomicReadWriteLockEnterEvent  ", false, false,None));
      eventList.append(new EventDesc("AtomicReadWriteLockExitEventGen", typSyncActions, nextId(), plusAtonicMethodId(plusInterleaveFields(fieldListLock())) , " extends AtomicReadWriteLockExitEvent  ", false, false,None));
      eventList.append(new EventDesc("AtomicNonBlockingEventGen", typSyncActions, nextId(), plusAtonicMethodId(plusInterleaveFields(fieldListNonBlocking())) , " extends AtomicNonBlockingEvent  ", false, false,None));
      
      eventList.append(runStartAndEnd("RunStartEventGen", " extends RunStartEvent  ",  typControl));
      eventList.append(runStartAndEnd("RunEndEventGen", " extends RunEndEvent  "  , typControl));
      eventList.append(warning("LoopWarningEventGen", " extends LoopWarningEvent  ", typControl));

      eventList;

    }

}