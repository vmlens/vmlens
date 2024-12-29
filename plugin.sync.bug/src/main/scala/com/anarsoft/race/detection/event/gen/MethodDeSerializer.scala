package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.directmemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.load.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.nonvolatilefield.*
import com.anarsoft.race.detection.event.syncaction.*

import java.nio.ByteBuffer;

class MethodDeSerializer extends DeserializeStrategy[LoadedMethodEvent] {
  val eventArraySize: Int = 21
  val blockSize: Int = 21 * 10000;


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