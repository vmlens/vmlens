package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.directMemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.monitor.*
import com.anarsoft.race.detection.event.nonVolatileField.*
import com.anarsoft.race.detection.event.syncAction.*

import java.nio.ByteBuffer;


class ThreadJoinedEventGen(
                            val threadId: Long
                            , val joinedThreadId: Long
                            , val programCounter: Int
                            , val methodCounter: Int
                            , val loopId: Int
                            , val runId: Int
                            , val runPosition: Int
                          ) extends ThreadJoinedEvent {
  override def toString() = {
    var text = "ThreadJoinedEventGen"
    text = text + ", threadId:" + threadId
    text = text + ", joinedThreadId:" + joinedThreadId
    text = text + ", programCounter:" + programCounter
    text = text + ", methodCounter:" + methodCounter
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: ThreadJoinedEventGen => {
        if (threadId != that.threadId) {
          false;
        }
        else if (joinedThreadId != that.joinedThreadId) {
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


object ThreadJoinedEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new ThreadJoinedEventGen(

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