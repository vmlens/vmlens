package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.monitor._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class FieldDeSerializer extends DeserializeStrategy[LoadedNonVolatileFieldEvent] {
  def eventArraySize() = 51

  val blockSize = 51 * 10000;


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