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


class ThreadJoinedEventGen(
                            val threadId: Long


                            , val joinedThreadId: Long


                            , val programCounter: Int


                            , val methodCounter: Int


                            , val loopId: Int


                            , val runId: Int


                            , val runPosition: Int


                          ) extends SyncAction {
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


  def accept(visitor: SyncActionsVisitor) {
    visitor.visit(this);
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


  def applyFromScalaEvent(data: ByteBuffer) = {
    val result = new ThreadJoinedEventGen(

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


class SortOrigThreadJoinedEventGen extends Comparator[ThreadJoinedEventGen] {
  def compare(o1: ThreadJoinedEventGen, o2: ThreadJoinedEventGen) = {

    if (o1.threadId != o2.threadId) {
      java.lang.Long.compare(o1.threadId, o2.threadId)
    }
    else if (o1.methodCounter != o2.methodCounter) {
      java.lang.Integer.compare(o1.methodCounter, o2.methodCounter)
    }
    else if (o1.programCounter != o2.programCounter) {
      java.lang.Integer.compare(o1.programCounter, o2.programCounter)
    }
    else if (o1.joinedThreadId != o2.joinedThreadId) {
      java.lang.Long.compare(o1.joinedThreadId, o2.joinedThreadId)
    }
    else {
      0;
    }


  }


}


class SortThreadJoinedEventGen extends Comparator[ThreadJoinedEventGen] {
  def compare(o1: ThreadJoinedEventGen, o2: ThreadJoinedEventGen) = {

    if (o1.threadId != o2.threadId) {
      java.lang.Long.compare(o1.threadId, o2.threadId)
    }
    else if (o1.programCounter != o2.programCounter) {
      java.lang.Integer.compare(o1.programCounter, o2.programCounter)
    }
    else if (o1.joinedThreadId != o2.joinedThreadId) {
      java.lang.Long.compare(o1.joinedThreadId, o2.joinedThreadId)
    }
    else if (o1.methodCounter != o2.methodCounter) {
      java.lang.Integer.compare(o1.methodCounter, o2.methodCounter)
    }
    else {
      0;
    }


  }


}




