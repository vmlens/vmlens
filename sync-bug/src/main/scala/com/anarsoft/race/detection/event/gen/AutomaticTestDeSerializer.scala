package com.anarsoft.race.detection.event.gen;

import java.io.DataInputStream

import com.anarsoft.race.detection.event.automatictest._
import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class AutomaticTestDeSerializer extends DeserializeStrategy[LoadedAutomaticTestEvent] {
   val eventArraySize : Int = 25
   val blockSize : Int =  25 * 10000;
  

    def deSerializeJavaEvent(buffer : DataInputStream) : LoadedAutomaticTestEvent = {
       val id = buffer.readByte();
       
       if( id == 28 ) {
          return AutomaticTestMethodEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 29 ) {
          return AutomaticTestSuccessEventGen.applyFromJavaEvent( buffer   );
       }
         throw new RuntimeException("id " + id + " could not be deserialized");
    }

}