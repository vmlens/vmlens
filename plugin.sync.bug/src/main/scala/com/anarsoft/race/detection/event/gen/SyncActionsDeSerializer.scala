package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.syncAction._;
import com.anarsoft.race.detection.event.nonVolatileField._;
import com.anarsoft.race.detection.event.directMemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class SyncActionsDeSerializer extends DeserializeStrategy[LoadedSyncActionEvent] {
   val eventArraySize: Int = 49
   val blockSize: Int = 49 * 10000;


   def deSerializeJavaEvent(buffer: ByteBuffer): LoadedSyncActionEvent = {
      val id = buffer.get();

      if (id == 6) {
         return VolatileAccessEventStaticGen.applyFromJavaEvent(buffer);
      }
      if (id == 7) {
         return VolatileAccessEventGen.applyFromJavaEvent(buffer);
      }
      if (id == 8) {
         return VolatileArrayAccessEventGen.applyFromJavaEvent(buffer);
      }
      if (id == 10) {
         return LockEnterEventGen.applyFromJavaEvent(buffer);
      }
      if (id == 11) {
         return LockExitEventGen.applyFromJavaEvent(buffer);
      }
      if (id == 12) {
         return StampedLockEnterEventGen.applyFromJavaEvent(buffer);
      }
      if (id == 13) {
         return StampedLockExitEventGen.applyFromJavaEvent(buffer);
      }
      if (id == 14) {
         return MonitorEnterEventGen.applyFromJavaEvent(buffer);
      }
      if (id == 15) {
         return MonitorExitEventGen.applyFromJavaEvent(buffer);
      }
      if (id == 18) {
         return ThreadStartEventGen.applyFromJavaEvent(buffer);
      }
      if (id == 19) {
         return ThreadJoinedEventGen.applyFromJavaEvent(buffer);
      }
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
      throw new RuntimeException("id " + id + " could not be deserialized");
   }

}