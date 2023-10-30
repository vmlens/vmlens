package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.directMemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.monitor.*
import com.anarsoft.race.detection.event.nonVolatileField.*
import com.anarsoft.race.detection.event.syncAction.*

import java.nio.ByteBuffer;


class LoopStartEventGen(
                         val loopId: Int
                       ) extends LoopStartEvent {
  override def toString() = {
    var text = "LoopStartEventGen"
    text = text + ", loopId:" + loopId
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: LoopStartEventGen => {
        if (loopId != that.loopId) {
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
    );
    result;
  }

}