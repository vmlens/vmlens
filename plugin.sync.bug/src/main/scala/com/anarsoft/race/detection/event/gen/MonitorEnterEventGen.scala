package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.control.*
import com.anarsoft.race.detection.event.directmemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.nonvolatile.*

import java.nio.ByteBuffer;


class MonitorEnterEventGen(
                            val threadIndex: Int
                            , val order: Int
                            , val methodCounter: Int
                            , val objectHashCode: Long
                            , val methodId: Int
                            , val bytecodePosition: Int
                            , val loopId: Int
                            , val runId: Int
                            , val runPosition: Int
                          ) extends MonitorEnterEvent {
  override def toString() = {
    var text = "MonitorEnterEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", order:" + order
    text = text + ", methodCounter:" + methodCounter
    text = text + ", objectHashCode:" + objectHashCode
    text = text + ", methodId:" + methodId
    text = text + ", bytecodePosition:" + bytecodePosition
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: MonitorEnterEventGen => {
        if (threadIndex != that.threadIndex) {
          false;
        }
        else if (order != that.order) {
          false;
        }
        else if (methodCounter != that.methodCounter) {
          false;
        }
        else if (objectHashCode != that.objectHashCode) {
          false;
        }
        else if (methodId != that.methodId) {
          false;
        }
        else if (bytecodePosition != that.bytecodePosition) {
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


object MonitorEnterEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new MonitorEnterEventGen(

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
      ,
      data.getInt()
      ,
      data.getInt()
    );
    result;
  }

}