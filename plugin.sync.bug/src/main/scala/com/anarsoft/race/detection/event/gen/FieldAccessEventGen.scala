package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.monitor._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;


class FieldAccessEventGen(
                           val threadId: Long
                           , val programCounter: Int
                           , val fieldId: Int
                           , val methodCounter: Int
                           , val operation: Int
                           , val methodId: Int
                           , val stackTraceIncomplete: Boolean
                           , val objectHashCode: Long
                           , var slidingWindowId: Int
                           , val showSharedMemory: Boolean
                           , val loopId: Int
                           , val runId: Int
                           , val runPosition: Int
                         ) extends NonVolatileFieldAccessEvent {
  override def toString() = {
    var text = "FieldAccessEventGen"
    text = text + ", threadId:" + threadId
    text = text + ", programCounter:" + programCounter
    text = text + ", fieldId:" + fieldId
    text = text + ", methodCounter:" + methodCounter
    text = text + ", operation:" + operation
    text = text + ", methodId:" + methodId
    text = text + ", stackTraceIncomplete:" + stackTraceIncomplete
    text = text + ", objectHashCode:" + objectHashCode
    text = text + ", slidingWindowId:" + slidingWindowId
    text = text + ", showSharedMemory:" + showSharedMemory
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: FieldAccessEventGen => {
        if (threadId != that.threadId) {
          false;
        }
        else if (programCounter != that.programCounter) {
          false;
        }
        else if (fieldId != that.fieldId) {
          false;
        }
        else if (methodCounter != that.methodCounter) {
          false;
        }
        else if (operation != that.operation) {
          false;
        }
        else if (methodId != that.methodId) {
          false;
        }
        else if (stackTraceIncomplete != that.stackTraceIncomplete) {
          false;
        }
        else if (objectHashCode != that.objectHashCode) {
          false;
        }
        else if (slidingWindowId != that.slidingWindowId) {
          false;
        }
        else if (showSharedMemory != that.showSharedMemory) {
          false;
        }
        else if (loopId != that.loopId) {
          false;
        }
        else if (runId != that.runId) {
          false;
        }
        else if (runPosition != that.runPosition) {
          false;
        }
        else
          true;
      }
      case _ => false
    }
  }
}


object FieldAccessEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new FieldAccessEventGen(

      data.getLong()
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      if (data.get() == 1.asInstanceOf[Byte]) {
        true
      } else {
        false
      }
      ,
      data.getLong()
      ,
      0
      ,
      if (data.get() == 1.asInstanceOf[Byte]) {
        true
      } else {
        false
      }
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
    );
    result;
  }

}