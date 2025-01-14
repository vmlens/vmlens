package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.control.*
import com.anarsoft.race.detection.event.directmemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.nonvolatilefield.*

import java.nio.ByteBuffer;


class VolatileAccessEventStaticGen(
                                    val threadIndex: Int
                                    , val order: Int
                                    , val fieldId: Int
                                    , val methodCounter: Int
                                    , val methodId: Int
                                    , val isWrite: Boolean
                                    , val loopId: Int
                                    , val runId: Int
                                    , val runPosition: Int
                                  ) extends VolatileAccessEventStatic {
  override def toString() = {
    var text = "VolatileAccessEventStaticGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", order:" + order
    text = text + ", fieldId:" + fieldId
    text = text + ", methodCounter:" + methodCounter
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
        if (threadIndex != that.threadIndex) {
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
      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
    );
    result;
  }

}