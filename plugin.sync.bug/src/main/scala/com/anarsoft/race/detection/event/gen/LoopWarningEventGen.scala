package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.monitor._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;


class LoopWarningEventGen(
                           val loopId: Int
                           , val runId: Int
                         ) extends LoopWarningEvent {
  override def toString() = {
    var text = "LoopWarningEventGen"
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: LoopWarningEventGen => {
        if (loopId != that.loopId) {
          false;
        }
        else if (runId != that.runId) {
          false;
        }
        else
          true;
      }
      case _ => false
    }
  }
}


object LoopWarningEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new LoopWarningEventGen(

      data.getInt()
      ,
      data.getInt()
    );
    result;
  }

}