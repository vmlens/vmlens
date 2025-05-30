package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;
import com.anarsoft.race.detection.event.load._;

class ControlDeSerializer extends DeserializeStrategy[LoadedControlEvent] {
   val eventArraySize : Int = 13
   val blockSize : Int =  13 * 10000;
  

    def deSerializeJavaEvent(buffer : ByteBuffer) : LoadedControlEvent = {
       val id = buffer.get();
       
       if( id == 20 ) {
          return LoopStartEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 21 ) {
          return LoopEndEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 22 ) {
          return RunStartEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 23 ) {
          return RunEndEventGen.applyFromJavaEvent( buffer   );
       }
       if( id == 24 ) {
          return LoopWarningEventGen.applyFromJavaEvent( buffer   );
       }
         throw new RuntimeException("id " + id + " could not be deserialized");
    }

}