package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.load.*
import com.anarsoft.race.detection.event.nonvolatile.*

import java.nio.ByteBuffer;

class NonVolatileDeSerializer extends DeserializeStrategy[LoadedNonVolatileEvent] {
  val eventArraySize: Int = 49
  val blockSize: Int = 49 * 10000;


  def deSerializeJavaEvent(buffer: ByteBuffer): LoadedNonVolatileEvent = {
    val id = buffer.get();

    if (id == 2) {
      return FieldAccessEventGen.applyFromJavaEvent(buffer);
    }
    if (id == 4) {
      return FieldAccessEventStaticGen.applyFromJavaEvent(buffer);
    }
    if (id == 5) {
      return ArrayAccessEventGen.applyFromJavaEvent(buffer);
    }
    throw new RuntimeException("id " + id + " could not be deserialized");
  }

}