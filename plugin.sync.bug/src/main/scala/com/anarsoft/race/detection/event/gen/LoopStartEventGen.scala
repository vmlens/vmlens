package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatilefield._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class LoopStartEventGen(
                         val loopId: Int
                         , val runId: Int
                       ) extends LoopStartEvent {
  override def toString() = {
    var text = "LoopStartEventGen"
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: LoopStartEventGen => {
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


object LoopStartEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new LoopStartEventGen(

      data.getInt()
      ,
      data.getInt()
    );
    result;
  }

}