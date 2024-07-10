package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.monitor._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;


class MonitorExitEventGen(
                           val threadIndex: Int
                           , val order: Int
                           , val monitorId: Int
                           , val methodCounter: Int
                           , val methodId: Int
                           , val position: Int
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
    text = text + ", position:" + position
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
        else if (position != that.position) {
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