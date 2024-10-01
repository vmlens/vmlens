package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;


class VolatileAccessEventGen(
                              val threadIndex: Int
                              , val order: Int
                              , val fieldId: Int
                              , val methodCounter: Int
                              , val methodId: Int
                              , val operation: Int
                              , val objectHashCode: Long
                              , val loopId: Int
                              , val runId: Int
                              , val runPosition: Int
                            ) extends VolatileAccessEvent {
  override def toString() = {
    var text = "VolatileAccessEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", order:" + order
    text = text + ", fieldId:" + fieldId
    text = text + ", methodCounter:" + methodCounter
    text = text + ", methodId:" + methodId
    text = text + ", operation:" + operation
    text = text + ", objectHashCode:" + objectHashCode
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: VolatileAccessEventGen => {
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
        else if (operation != that.operation) {
          false;
        }
        else if (objectHashCode != that.objectHashCode) {
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


object VolatileAccessEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new VolatileAccessEventGen(

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
      data.getInt()
      ,
      data.getLong()
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