package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class DirectMemoryDeSerializer extends DeserializeStrategy[LoadedDirectMemoryEvent] {
   val eventArraySize : Int = 0
   val blockSize : Int =  0 * 10000;
  

    def deSerializeJavaEvent(buffer : ByteBuffer) : LoadedDirectMemoryEvent = {
       val id = buffer.get();
       
         throw new RuntimeException("id " + id + " could not be deserialized");
    }

}