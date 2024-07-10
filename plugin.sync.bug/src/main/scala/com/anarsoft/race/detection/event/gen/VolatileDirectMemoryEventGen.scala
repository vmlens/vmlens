package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.directMemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.monitor.*
import com.anarsoft.race.detection.event.nonVolatileField.*
import com.anarsoft.race.detection.event.syncAction.*

import java.nio.ByteBuffer;


class VolatileDirectMemoryEventGen(
                                    val threadIndex: Int
                                    , val methodCounter: Int
                                    , val objectHashCode: Long
                                    , var stackTraceOrdinal: Int
                                    , val operation: Int
                                    , val order: Int
                                    , val loopId: Int
                                    , val runId: Int
                                    , val runPosition: Int
                                  ) extends VolatileDirectMemoryEvent {
  override def toString() = {
    var text = "VolatileDirectMemoryEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", methodCounter:" + methodCounter
    text = text + ", objectHashCode:" + objectHashCode
    text = text + ", stackTraceOrdinal:" + stackTraceOrdinal
    text = text + ", operation:" + operation
    text = text + ", order:" + order
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: VolatileDirectMemoryEventGen => {
        if (threadIndex != that.threadIndex) {
          false;
        }
        else if (methodCounter != that.methodCounter) {
          false;
        }
        else if (objectHashCode != that.objectHashCode) {
          false;
        }
        else if (stackTraceOrdinal != that.stackTraceOrdinal) {
          false;
        }
        else if (operation != that.operation) {
          false;
        }
        else if (order != that.order) {
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


object VolatileDirectMemoryEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new VolatileDirectMemoryEventGen(

      data.getInt()
      ,
      data.getInt()
      ,
      data.getLong()
      ,
      0
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
    );
    result;
  }

}