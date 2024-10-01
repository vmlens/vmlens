package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class InterleaveDeSerializer extends DeserializeStrategy[LoadedInterleaveEvent] {
  val eventArraySize: Int = 13
  val blockSize: Int = 13 * 10000;


  def deSerializeJavaEvent(buffer: ByteBuffer): LoadedInterleaveEvent = {
    val id = buffer.get();

    if (id == 24) {
      return LoopStartEventGen.applyFromJavaEvent(buffer);
    }
    if (id == 25) {
      return LoopEndEventGen.applyFromJavaEvent(buffer);
    }
    if (id == 26) {
      return RunStartEventGen.applyFromJavaEvent(buffer);
    }
    if (id == 27) {
      return RunEndEventGen.applyFromJavaEvent(buffer);
    }
    if (id == 28) {
      return LoopWarningEventGen.applyFromJavaEvent(buffer);
    }
    throw new RuntimeException("id " + id + " could not be deserialized");
  }

}