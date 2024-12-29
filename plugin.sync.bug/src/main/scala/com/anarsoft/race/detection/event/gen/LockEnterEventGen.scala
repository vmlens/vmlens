package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.directmemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.nonvolatilefield.*
import com.anarsoft.race.detection.event.syncaction.*

import java.nio.ByteBuffer;


class LockEnterEventGen(
                         val threadIndex: Int
                         , val order: Int
                         , val monitorId: Int
                         , val methodCounter: Int
                         , val isShared: Boolean
                         , val lockTyp: Int
                         , val loopId: Int
                         , val runId: Int
                         , val runPosition: Int
                       ) extends LockEnterEvent {
  override def toString() = {
    var text = "LockEnterEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", order:" + order
    text = text + ", monitorId:" + monitorId
    text = text + ", methodCounter:" + methodCounter
    text = text + ", isShared:" + isShared
    text = text + ", lockTyp:" + lockTyp
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: LockEnterEventGen => {
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


object LockEnterEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new LockEnterEventGen(

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
    );
    result;
  }

}