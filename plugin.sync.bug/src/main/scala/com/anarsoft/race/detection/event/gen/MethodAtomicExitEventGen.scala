package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.monitor._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;


class MethodAtomicExitEventGen(
                                val threadIndex: Int
                                , val methodId: Int
                                , val methodCounter: Int
                                , val hasCallback: Byte
                                , val loopId: Int
                                , val runId: Int
                                , val runPosition: Int
                              ) extends MethodAtomicExitEvent {
  override def toString() = {
    var text = "MethodAtomicExitEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", methodId:" + methodId
    text = text + ", methodCounter:" + methodCounter
    text = text + ", hasCallback:" + hasCallback
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: MethodAtomicExitEventGen => {
        if (threadIndex != that.threadIndex) {
          false;
        }
        else if (methodId != that.methodId) {
          false;
        }
        else if (methodCounter != that.methodCounter) {
          false;
        }
        else if (hasCallback != that.hasCallback) {
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


object MethodAtomicExitEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new MethodAtomicExitEventGen(

      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      data.get()
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