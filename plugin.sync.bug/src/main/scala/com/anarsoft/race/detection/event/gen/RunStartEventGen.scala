package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;


class RunStartEventGen(
                        val loopId: Int
                        , val runId: Int
                      ) extends RunStartEvent {
  override def toString() = {
    var text = "RunStartEventGen"
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: RunStartEventGen => {
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


object RunStartEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new RunStartEventGen(

      data.getInt()
      ,
      data.getInt()
    );
    result;
  }

}