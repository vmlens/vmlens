package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.directMemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.load.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.monitor.*
import com.anarsoft.race.detection.event.nonVolatileField.*
import com.anarsoft.race.detection.event.syncAction.*

import java.nio.ByteBuffer;

class FieldDeserializer extends DeserializeStrategy[LoadedNonVolatileFieldEvent] {
  def deSerializeJavaEvent(buffer: ByteBuffer): LoadedNonVolatileFieldEvent = {
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