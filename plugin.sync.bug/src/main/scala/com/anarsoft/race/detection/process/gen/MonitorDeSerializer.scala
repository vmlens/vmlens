package com.anarsoft.race.detection.process.gen;

import java.nio.ByteBuffer
import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.nonVolatileField.ApplyFieldEventVisitor
import com.anarsoft.race.detection.process.syncAction.SyncAction
import com.anarsoft.race.detection.process.method.ApplyMethodEventVisitor
import com.anarsoft.race.detection.process.monitor.MonitorEvent
import com.anarsoft.race.detection.process.directMemory._;
import com.anarsoft.race.detection.process.scheduler._

class MonitorDeSerializer extends ReadStrategy[MonitorEvent] {
  def eventArraySize() = 45

  val blockSize = 45 * 10000;


  def deSerializeJavaEvent(buffer: ByteBuffer) = {

    val id = buffer.get();


    if (id == 14) {
      MonitorEnterEventGen.applyFromJavaEvent(buffer);
    }
    else if (id == 15) {
      MonitorExitEventGen.applyFromJavaEvent(buffer);
    }
    else {
      throw new RuntimeException("id " + id + " could not be deserialized");
    }


  }


  def deSerializeScalaEvent(buffer: ByteBuffer) = {

    val id = buffer.get();


    if (id == 14) {
      MonitorEnterEventGen.applyFromScalaEvent(buffer);
    }
    else if (id == 15) {
      MonitorExitEventGen.applyFromScalaEvent(buffer);
    }
    else {
      throw new RuntimeException("id " + id + " could not be deserialized");
    }


  }




}