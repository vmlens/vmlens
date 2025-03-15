package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class DirectMemoryDeSerializer extends DeserializeStrategy[LoadedDirectMemoryEvent] {
   val eventArraySize : Int = 33
   val blockSize : Int =  33 * 10000;
  

    def deSerializeJavaEvent(buffer : ByteBuffer) : LoadedDirectMemoryEvent = {
       val id = buffer.get();
       
       if( id == 9 ) {
          return VolatileDirectMemoryEventGen.applyFromJavaEvent( buffer   );
       }
         throw new RuntimeException("id " + id + " could not be deserialized");
    }

}