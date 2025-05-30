package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class InterleaveDeSerializer extends DeserializeStrategy[LoadedInterleaveActionEvent] {
   val eventArraySize : Int = 45
   val blockSize : Int =  45 * 10000;
  

    def deSerializeJavaEvent(buffer : ByteBuffer) : LoadedInterleaveActionEvent = {
       val id = buffer.get();
       
       if( id == 5 ) {
          return VolatileFieldAccessEventStaticGen.applyFromJavaEvent( buffer   );
       }
       if( id == 6 ) {
          return VolatileFieldAccessEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 7 ) {
          return VolatileArrayAccessEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 8 ) {
          return LockEnterEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 9 ) {
          return LockExitEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 10 ) {
          return MonitorEnterEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 11 ) {
          return MonitorExitEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 14 ) {
          return ThreadStartEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 15 ) {
          return ThreadJoinedEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 16 ) {
          return AtomicReadWriteLockEnterEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 17 ) {
          return AtomicReadWriteLockExitEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 18 ) {
          return AtomicNonBlockingEventGen.applyFromJavaEvent( buffer   );
       }
         throw new RuntimeException("id " + id + " could not be deserialized");
    }

}