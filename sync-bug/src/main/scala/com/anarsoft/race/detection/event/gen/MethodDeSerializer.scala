package com.anarsoft.race.detection.event.gen;

import java.io.DataInputStream

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class MethodDeSerializer extends DeserializeStrategy[LoadedMethodEvent] {
   val eventArraySize : Int = 21
   val blockSize : Int =  21 * 10000;
  

    def deSerializeJavaEvent(buffer : DataInputStream) : LoadedMethodEvent = {
       val id = buffer.readByte();
       
       if( id == 20 ) {
          return MethodEnterEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 21 ) {
          return MethodExitEventGen.applyFromJavaEvent( buffer   );
       }
         throw new RuntimeException("id " + id + " could not be deserialized");
    }

}