package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.control.*
import com.anarsoft.race.detection.event.directmemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.load.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.nonvolatile.*

import java.nio.ByteBuffer;

class ControlDeSerializer extends DeserializeStrategy[LoadedControlEvent] {
  val eventArraySize: Int = 13
  val blockSize: Int = 13 * 10000;


  def deSerializeJavaEvent(buffer: ByteBuffer): LoadedControlEvent = {
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