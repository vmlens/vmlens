package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatilefield._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class MonitorExitEventGen(
                           val threadIndex: Int
                           , val order: Int
                           , val monitorId: Int
                           , val methodCounter: Int
                           , val methodId: Int
                           , val bytecodePosition: Int
                           , val loopId: Int
                           , val runId: Int
                           , val runPosition: Int
                         ) extends MonitorExitEvent {
  override def toString() = {
    var text = "MonitorExitEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", order:" + order
    text = text + ", monitorId:" + monitorId
    text = text + ", methodCounter:" + methodCounter
    text = text + ", methodId:" + methodId
    text = text + ", bytecodePosition:" + bytecodePosition
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: MonitorExitEventGen => {
        if (threadIndex != that.threadIndex) {
          false;
        }
        else if (order != that.order) {
          false;
        }
        else if (monitorId != that.monitorId) {
          false;
        }
        else if (methodCounter != that.methodCounter) {
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


object MonitorExitEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new MonitorExitEventGen(

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
      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
    );
    result;
  }

}