package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.directMemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.load.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.monitor.*
import com.anarsoft.race.detection.event.nonVolatileField.*
import com.anarsoft.race.detection.event.syncAction.*

import java.nio.ByteBuffer;

class InterleaveDeSerializer extends LoadStrategy[LoadedInterleaveEvent] {
  val blockSize = 26 * 10000;

  def eventArraySize() = 26


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