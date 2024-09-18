package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.monitor._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;


class LoopEndEventGen(
                       val loopId: Int
                       , val runId: Int
                       , val status: Int
                     ) extends LoopEndEvent {
  override def toString() = {
    var text = "LoopEndEventGen"
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", status:" + status
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: LoopEndEventGen => {
        if (loopId != that.loopId) {
          false;
        }
        else if (runId != that.runId) {
          false;
        }
        else if (status != that.status) {
          false;
        }
        else
          true;
      }
      case _ => false
    }
  }
}


object LoopEndEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new LoopEndEventGen(

      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
    );
    result;
  }

}