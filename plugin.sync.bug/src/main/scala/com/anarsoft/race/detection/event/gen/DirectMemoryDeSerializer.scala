package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.directMemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.load.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.monitor.*
import com.anarsoft.race.detection.event.nonVolatileField.*
import com.anarsoft.race.detection.event.syncAction.*

import java.nio.ByteBuffer;

class DirectMemoryDeSerializer extends LoadStrategy[LoadedDirectMemoryEvent] {
  val blockSize = 37 * 10000;

  def eventArraySize() = 37


  def deSerializeJavaEvent(buffer: ByteBuffer): LoadedDirectMemoryEvent = {
    val id = buffer.get();

    if (id == 9) {
      return VolatileDirectMemoryEventGen.applyFromJavaEvent(buffer);
    }
    throw new RuntimeException("id " + id + " could not be deserialized");
  }

}