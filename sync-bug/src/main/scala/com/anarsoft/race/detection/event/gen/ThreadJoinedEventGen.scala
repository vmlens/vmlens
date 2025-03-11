package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.control.*
import com.anarsoft.race.detection.event.directmemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.nonvolatile.*

import java.nio.ByteBuffer;


class ThreadJoinedEventGen(
                            val threadIndex: Int
                            , val bytecodePosition: Int
                            , val methodId: Int
                            , val joinedThreadIndex: Int
                            , val methodCounter: Int
                            , val loopId: Int
                            , val runId: Int
                            , val runPosition: Int
                          ) extends ThreadJoinedEvent {
  override def toString() = {
    var text = "ThreadJoinedEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", bytecodePosition:" + bytecodePosition
    text = text + ", methodId:" + methodId
    text = text + ", joinedThreadIndex:" + joinedThreadIndex
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
        else if (bytecodePosition != that.bytecodePosition) {
          false;
        }
        else if (methodId != that.methodId) {
          false;
        }
        else if (joinedThreadIndex != that.joinedThreadIndex) {
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