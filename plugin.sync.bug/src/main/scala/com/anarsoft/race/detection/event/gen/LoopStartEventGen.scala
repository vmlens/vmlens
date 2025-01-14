package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.control.*
import com.anarsoft.race.detection.event.directmemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.nonvolatilefield.*

import java.nio.ByteBuffer;


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