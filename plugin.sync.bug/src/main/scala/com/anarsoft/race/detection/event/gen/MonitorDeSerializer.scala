package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.monitor._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class MonitorDeserializer extends DeserializeStrategy[LoadedMonitorEvent] {
  def deSerializeJavaEvent(buffer: ByteBuffer): LoadedMonitorEvent = {
    val id = buffer.get();

    if (id == 14) {
      return MonitorEnterEventGen.applyFromJavaEvent(buffer);
    }
    if (id == 15) {
      return MonitorExitEventGen.applyFromJavaEvent(buffer);
    }
    throw new RuntimeException("id " + id + " could not be deserialized");
  }
}