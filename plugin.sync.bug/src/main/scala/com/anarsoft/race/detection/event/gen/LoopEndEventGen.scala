package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.directMemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.monitor.*
import com.anarsoft.race.detection.event.nonVolatileField.*
import com.anarsoft.race.detection.event.syncAction.*

import java.nio.ByteBuffer;


class LoopEndEventGen(
                       val loopId: Int
                       , val status: Int
                     ) extends LoopEndEvent {
  override def toString() = {
    var text = "LoopEndEventGen"
    text = text + ", loopId:" + loopId
    text = text + ", status:" + status
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: LoopEndEventGen => {
        if (loopId != that.loopId) {
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
    );
    result;
  }

}