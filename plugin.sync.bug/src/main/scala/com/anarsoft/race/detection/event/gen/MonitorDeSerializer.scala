package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.directMemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.load.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.monitor.*
import com.anarsoft.race.detection.event.nonVolatileField.*
import com.anarsoft.race.detection.event.syncAction.*

import java.nio.ByteBuffer;

class MonitorDeSerializer extends DeSerializeStrategy[LoadedMonitorEvent] {
  def eventArraySize() = 45

  val blockSize = 45 * 10000;


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