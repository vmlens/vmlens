package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.monitor._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;


class StampedLockEnterEventGen(
                                val threadIndex: Int
                                , val order: Int
                                , val monitorId: Int
                                , val methodCounter: Int
                                , val isShared: Boolean
                                , val lockTyp: Int
                                , val stampedLockMethodId: Int
                                , val loopId: Int
                                , val runId: Int
                                , val runPosition: Int
                              ) extends StampedLockEnter {
  override def toString() = {
    var text = "StampedLockEnterEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", order:" + order
    text = text + ", monitorId:" + monitorId
    text = text + ", methodCounter:" + methodCounter
    text = text + ", isShared:" + isShared
    text = text + ", lockTyp:" + lockTyp
    text = text + ", stampedLockMethodId:" + stampedLockMethodId
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: StampedLockEnterEventGen => {
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
        else if (isShared != that.isShared) {
          false;
        }
        else if (lockTyp != that.lockTyp) {
          false;
        }
        else if (stampedLockMethodId != that.stampedLockMethodId) {
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


object StampedLockEnterEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new StampedLockEnterEventGen(

      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      if (data.get() == 1.asInstanceOf[Byte]) {
        true
      } else {
        false
      }
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