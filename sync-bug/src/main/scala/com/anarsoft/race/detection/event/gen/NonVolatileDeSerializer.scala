package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class NonVolatileDeSerializer extends DeserializeStrategy[LoadedNonVolatileEvent] {
   val eventArraySize : Int = 49
   val blockSize : Int =  49 * 10000;
  

    def deSerializeJavaEvent(buffer : ByteBuffer) : LoadedNonVolatileEvent = {
       val id = buffer.get();
       
       if( id == 2 ) {
          return FieldAccessEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 4 ) {
          return FieldAccessEventStaticGen.applyFromJavaEvent( buffer   );
       }
       if( id == 5 ) {
          return ArrayAccessEventGen.applyFromJavaEvent( buffer   );
       }
         throw new RuntimeException("id " + id + " could not be deserialized");
    }

}