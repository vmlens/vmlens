package com.anarsoft.race.detection.event.gen;

import java.io.DataInputStream

import com.anarsoft.race.detection.event.automatictest._
import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class ControlDeSerializer extends DeserializeStrategy[LoadedControlEvent] {
   val eventArraySize : Int = 17
   val blockSize : Int =  17 * 10000;
  

    def deSerializeJavaEvent(buffer : DataInputStream) : LoadedControlEvent = {
       val id = buffer.readByte();
       
       if( id == 25 ) {
          return RunStartEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 26 ) {
          return RunEndEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 27 ) {
          return LoopWarningEventGen.applyFromJavaEvent( buffer   );
       }
         throw new RuntimeException("id " + id + " could not be deserialized");
    }

}