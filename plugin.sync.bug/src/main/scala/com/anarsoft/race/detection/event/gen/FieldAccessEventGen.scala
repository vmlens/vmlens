package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.directmemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.nonvolatilefield.*
import com.anarsoft.race.detection.event.syncaction.*

import java.nio.ByteBuffer;


class FieldAccessEventGen(
                           val threadIndex: Int
                           , val fieldId: Int
                           , val methodCounter: Int
                           , val operation: Int
                           , val methodId: Int
                           , val objectHashCode: Long
                           , val loopId: Int
                           , val runId: Int
                           , val runPosition: Int
                         ) extends NonVolatileFieldAccessEvent {
  override def toString() = {
    var text = "FieldAccessEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", fieldId:" + fieldId
    text = text + ", methodCounter:" + methodCounter
    text = text + ", operation:" + operation
    text = text + ", methodId:" + methodId
    text = text + ", objectHashCode:" + objectHashCode
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: FieldAccessEventGen => {
        if (threadIndex != that.threadIndex) {
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


object FieldAccessEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new FieldAccessEventGen(

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