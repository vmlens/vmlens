package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.directMemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.monitor.*
import com.anarsoft.race.detection.event.nonVolatileField.*
import com.anarsoft.race.detection.event.syncAction.*

import java.nio.ByteBuffer;


class ThreadJoinedEventGen(
                            val threadIndex: Int
                            , val joinedThreadId: Long
                            , val methodCounter: Int
                            , val loopId: Int
                            , val runId: Int
                            , val runPosition: Int
                          ) extends ThreadJoinedEvent {
  override def toString() = {
    var text = "ThreadJoinedEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", joinedThreadId:" + joinedThreadId
    text = text + ", methodCounter:" + methodCounter
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: ThreadJoinedEventGen => {
        if (threadIndex != that.threadIndex) {
          false;
        }
        else if (joinedThreadId != that.joinedThreadId) {
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
    );
    result;
  }

}