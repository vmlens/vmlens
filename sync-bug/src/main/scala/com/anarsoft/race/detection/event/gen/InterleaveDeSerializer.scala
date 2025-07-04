package com.anarsoft.race.detection.event.gen;

import java.io.DataInputStream

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class InterleaveDeSerializer extends DeserializeStrategy[LoadedInterleaveActionEvent] {
   val eventArraySize : Int = 45
   val blockSize : Int =  45 * 10000;
  

    def deSerializeJavaEvent(buffer : DataInputStream) : LoadedInterleaveActionEvent = {
       val id = buffer.readByte();
       
       if( id == 4 ) {
          return VolatileFieldAccessEventStaticGen.applyFromJavaEvent( buffer   );
       }
       if( id == 5 ) {
          return VolatileFieldAccessEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 6 ) {
          return AtomicNonBlockingEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 7 ) {
          return LockEnterEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 8 ) {
          return LockExitEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 9 ) {
          return AtomicReadWriteLockExitEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 10 ) {
          return AtomicReadWriteLockEnterEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 11 ) {
          return MonitorEnterEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 12 ) {
          return MonitorExitEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 13 ) {
          return BarrierWaitEnterEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 14 ) {
          return BarrierWaitExitEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 15 ) {
          return BarrierNotifyEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 16 ) {
          return ConditionWaitEnterEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 17 ) {
          return ConditionWaitExitEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 18 ) {
          return ConditionNotifyEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 21 ) {
          return ThreadStartEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 22 ) {
          return ThreadJoinedEventGen.applyFromJavaEvent( buffer   );
       }
         throw new RuntimeException("id " + id + " could not be deserialized");
    }

}