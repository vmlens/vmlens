package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.control.*
import com.anarsoft.race.detection.event.directmemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.nonvolatile.*

import java.nio.ByteBuffer;


class VolatileArrayAccessEventGen(
                                   val threadIndex: Int
                                   , val index: Long
                                   , val methodCounter: Int
                                   , val methodId: Int
                                   , val operation: Int
                                   , val objectHashCode: Long
                                   , val loopId: Int
                                   , val runId: Int
                                   , val runPosition: Int
                                 ) extends VolatileArrayAccessEvent {
  override def toString() = {
    var text = "VolatileArrayAccessEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", index:" + index
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
      case that: VolatileArrayAccessEventGen => {
        if (threadIndex != that.threadIndex) {
          false;
        }
        else if (index != that.index) {
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


object VolatileArrayAccessEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new VolatileArrayAccessEventGen(

      data.getInt()
      ,
      data.getLong()
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