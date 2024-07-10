package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.directMemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.monitor.*
import com.anarsoft.race.detection.event.nonVolatileField.*
import com.anarsoft.race.detection.event.syncAction.*

import java.nio.ByteBuffer;


class MethodExitEventGen(
                          val threadIndex: Int
                          , val methodId: Int
                          , val methodCounter: Int
                          , val loopId: Int
                          , val runId: Int
                          , val runPosition: Int
                        ) extends MethodExitEvent {
  override def toString() = {
    var text = "MethodExitEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", methodId:" + methodId
    text = text + ", methodCounter:" + methodCounter
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: MethodExitEventGen => {
        if (threadIndex != that.threadIndex) {
          false;
        }
        else if (methodId != that.methodId) {
          false;
        }
        else if (methodCounter != that.methodCounter) {
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


object MethodExitEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new MethodExitEventGen(

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