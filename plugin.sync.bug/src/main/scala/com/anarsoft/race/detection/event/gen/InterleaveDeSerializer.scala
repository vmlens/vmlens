package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.monitor._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class InterleaveDeSerializer extends DeserializeStrategy[LoadedInterleaveEvent] {
  def eventArraySize() = 26

  val blockSize = 26 * 10000;


  def deSerializeJavaEvent(buffer: ByteBuffer): LoadedInterleaveEvent = {
    val id = buffer.get();

    if (id == 20) {
      return MethodAtomicEnterEventGen.applyFromJavaEvent(buffer);
    }
    if (id == 21) {
      return MethodAtomicExitEventGen.applyFromJavaEvent(buffer);
    }
    if (id == 22) {
      return MethodCallbackEnterEventGen.applyFromJavaEvent(buffer);
    }
    if (id == 23) {
      return MethodCallbackExitEventGen.applyFromJavaEvent(buffer);
    }
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