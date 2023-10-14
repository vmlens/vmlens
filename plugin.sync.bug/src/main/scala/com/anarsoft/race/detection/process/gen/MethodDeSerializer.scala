package com.anarsoft.race.detection.process.gen;

import java.nio.ByteBuffer
import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.nonVolatileField.ApplyFieldEventVisitor
import com.anarsoft.race.detection.process.syncAction.SyncAction
import com.anarsoft.race.detection.process.method.ApplyMethodEventVisitor
import com.anarsoft.race.detection.process.monitor.MonitorEvent
import com.anarsoft.race.detection.process.directMemory._;
import com.anarsoft.race.detection.process.scheduler._

class MethodDeSerializer extends ReadStrategy[ApplyMethodEventVisitor] {
  def eventArraySize() = 17

  val blockSize = 17 * 10000;


  def deSerializeJavaEvent(buffer: ByteBuffer) = {

    val id = buffer.get();


    if (id == 16) {
      MethodEnterEventGen.applyFromJavaEvent(buffer);
    }
    else if (id == 17) {
      MethodExitEventGen.applyFromJavaEvent(buffer);
    }
    else {
      throw new RuntimeException("id " + id + " could not be deserialized");
    }


  }


  def deSerializeScalaEvent(buffer: ByteBuffer) = {

    val id = buffer.get();


    if (id == 16) {
      MethodEnterEventGen.applyFromScalaEvent(buffer);
    }
    else if (id == 17) {
      MethodExitEventGen.applyFromScalaEvent(buffer);
    }
    else {
      throw new RuntimeException("id " + id + " could not be deserialized");
    }


  }




}