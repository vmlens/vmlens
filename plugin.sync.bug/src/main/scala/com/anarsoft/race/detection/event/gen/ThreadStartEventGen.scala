package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;


class ThreadStartEventGen(
                           val threadIndex: Int
                           , val startedThreadIndex: Int
                           , val methodCounter: Int
                           , val loopId: Int
                           , val runId: Int
                           , val runPosition: Int
                         ) extends ThreadStartEvent {
  override def toString() = {
    var text = "ThreadStartEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", startedThreadIndex:" + startedThreadIndex
    text = text + ", methodCounter:" + methodCounter
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: ThreadStartEventGen => {
        if (threadIndex != that.threadIndex) {
          false;
        }
        else if (startedThreadIndex != that.startedThreadIndex) {
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


object ThreadStartEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new ThreadStartEventGen(

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