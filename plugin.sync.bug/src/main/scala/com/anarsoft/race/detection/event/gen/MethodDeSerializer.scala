package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.monitor._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class MethodDeserializer extends DeserializeStrategy[LoadedMethodEvent] {
  def deSerializeJavaEvent(buffer: ByteBuffer): LoadedMethodEvent = {
    val id = buffer.get();

    if (id == 16) {
      return MethodEnterEventGen.applyFromJavaEvent(buffer);
    }
    if (id == 17) {
      return MethodExitEventGen.applyFromJavaEvent(buffer);
    }
    throw new RuntimeException("id " + id + " could not be deserialized");
  }
}