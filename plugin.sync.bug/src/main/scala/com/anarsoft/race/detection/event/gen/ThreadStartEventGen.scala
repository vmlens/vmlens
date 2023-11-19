package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.monitor._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;


class ThreadStartEventGen(
                           val threadId: Long
                           , val startedThreadId: Long
                           , val programCounter: Int
                           , val methodCounter: Int
                           , val loopId: Int
                           , val runId: Int
                           , val runPosition: Int
                         ) extends ThreadStartEvent {
  override def toString() = {
    var text = "ThreadStartEventGen"
    text = text + ", threadId:" + threadId
    text = text + ", startedThreadId:" + startedThreadId
    text = text + ", programCounter:" + programCounter
    text = text + ", methodCounter:" + methodCounter
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: ThreadStartEventGen => {
        if (threadId != that.threadId) {
          false;
        }
        else if (startedThreadId != that.startedThreadId) {
          false;
        }
        else if (programCounter != that.programCounter) {
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

      data.getLong()
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