package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.control.*
import com.anarsoft.race.detection.event.directmemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.load.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.nonvolatilefield.*

import java.nio.ByteBuffer;

class DirectMemoryDeSerializer extends DeserializeStrategy[LoadedDirectMemoryEvent] {
  val eventArraySize: Int = 37
  val blockSize: Int = 37 * 10000;


  def deSerializeJavaEvent(buffer: ByteBuffer): LoadedDirectMemoryEvent = {
    val id = buffer.get();

    if (id == 9) {
      return VolatileDirectMemoryEventGen.applyFromJavaEvent(buffer);
    }
    throw new RuntimeException("id " + id + " could not be deserialized");
  }

}