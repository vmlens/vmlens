package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.monitor._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;


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