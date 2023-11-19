package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.monitor._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;


class VolatileAccessEventStaticGen(
                                    val threadId: Long
                                    , val programCounter: Int
                                    , val order: Int
                                    , val fieldId: Int
                                    , val methodCounter: Int
                                    , var slidingWindowId: Int
                                    , val methodId: Int
                                    , val isWrite: Boolean
                                    , val loopId: Int
                                    , val runId: Int
                                    , val runPosition: Int
                                  ) extends VolatileAccessEventStatic {
  override def toString() = {
    var text = "VolatileAccessEventStaticGen"
    text = text + ", threadId:" + threadId
    text = text + ", programCounter:" + programCounter
    text = text + ", order:" + order
    text = text + ", fieldId:" + fieldId
    text = text + ", methodCounter:" + methodCounter
    text = text + ", slidingWindowId:" + slidingWindowId
    text = text + ", methodId:" + methodId
    text = text + ", isWrite:" + isWrite
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: VolatileAccessEventStaticGen => {
        if (threadId != that.threadId) {
          false;
        }
        else if (programCounter != that.programCounter) {
          false;
        }
        else if (order != that.order) {
          false;
        }
        else if (fieldId != that.fieldId) {
          false;
        }
        else if (methodCounter != that.methodCounter) {
          false;
        }
        else if (slidingWindowId != that.slidingWindowId) {
          false;
        }
        else if (methodId != that.methodId) {
          false;
        }
        else if (isWrite != that.isWrite) {
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


object VolatileAccessEventStaticGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new VolatileAccessEventStaticGen(

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
      0
      ,
      data.getInt()
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