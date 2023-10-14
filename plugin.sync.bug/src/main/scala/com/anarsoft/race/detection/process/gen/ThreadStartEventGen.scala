package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.method._
import com.anarsoft.race.detection.process.syncAction._;
import com.anarsoft.race.detection.process.volatileField._;
import com.anarsoft.race.detection.process.monitor._;
import com.anarsoft.race.detection.process.nonVolatileField._;
import java.util.Comparator
import java.nio.ByteBuffer;
import java.io.DataOutputStream;
import com.anarsoft.race.detection.process.directMemory._;
import com.anarsoft.race.detection.process.scheduler._
import com.anarsoft.race.detection.process.interleave._;


class ThreadStartEventGen(
                           val threadId: Long


                           , val startedThreadId: Long


                           , val programCounter: Int


                           , val methodCounter: Int


                           , val loopId: Int


                           , val runId: Int


                           , val runPosition: Int


                         ) extends SyncAction {
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


  def accept(visitor: SyncActionsVisitor) {
    visitor.visit(this);
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


  def applyFromScalaEvent(data: ByteBuffer) = {
    val result = new ThreadStartEventGen(

      data.getLong()
      , data.getLong()
      , data.getInt()
      , data.getInt()
      , data.getInt()
      , data.getInt()
      , data.getInt()


    );


    result;

  }


}


class SortOrigThreadStartEventGen extends Comparator[ThreadStartEventGen] {
  def compare(o1: ThreadStartEventGen, o2: ThreadStartEventGen) = {

    if (o1.threadId != o2.threadId) {
      java.lang.Long.compare(o1.threadId, o2.threadId)
    }
    else if (o1.methodCounter != o2.methodCounter) {
      java.lang.Integer.compare(o1.methodCounter, o2.methodCounter)
    }
    else if (o1.programCounter != o2.programCounter) {
      java.lang.Integer.compare(o1.programCounter, o2.programCounter)
    }
    else if (o1.startedThreadId != o2.startedThreadId) {
      java.lang.Long.compare(o1.startedThreadId, o2.startedThreadId)
    }
    else {
      0;
    }


  }


}


class SortThreadStartEventGen extends Comparator[ThreadStartEventGen] {
  def compare(o1: ThreadStartEventGen, o2: ThreadStartEventGen) = {

    if (o1.threadId != o2.threadId) {
      java.lang.Long.compare(o1.threadId, o2.threadId)
    }
    else if (o1.programCounter != o2.programCounter) {
      java.lang.Integer.compare(o1.programCounter, o2.programCounter)
    }
    else if (o1.startedThreadId != o2.startedThreadId) {
      java.lang.Long.compare(o1.startedThreadId, o2.startedThreadId)
    }
    else if (o1.methodCounter != o2.methodCounter) {
      java.lang.Integer.compare(o1.methodCounter, o2.methodCounter)
    }
    else {
      0;
    }


  }


}




