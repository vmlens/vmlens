package com.anarsoft.race.detection.process.gen;

import java.nio.ByteBuffer
import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.nonVolatileField.ApplyFieldEventVisitor
import com.anarsoft.race.detection.process.syncAction.SyncAction
import com.anarsoft.race.detection.process.method.ApplyMethodEventVisitor
import com.anarsoft.race.detection.process.monitor.MonitorEvent
import com.anarsoft.race.detection.process.directMemory._;
import com.anarsoft.race.detection.process.scheduler._

class MonitorDeSerializer extends ReadStrategy[MonitorEvent] {
  def eventArraySize() = 45

  val blockSize = 45 * 10000;


  def deSerializeJavaEvent(buffer: ByteBuffer) = {

    val id = buffer.get();


    if (id == 22) {
      MonitorEnterEventGen.applyFromJavaEvent(buffer);
    }
    else
       
       
       if( id == 23 )
       {
          MonitorExitEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       
       if( id == 24 )
       {
          MonitorEnterEventGenInterleave.applyFromJavaEvent( buffer   );
       }
       else
       
       
       if( id == 25 )
       {
          MonitorExitEventGenInterleave.applyFromJavaEvent( buffer   );
       }
       else
       
       {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }
    
    
    def deSerializeScalaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();
       
       
       if( id == 22 )
       {
          MonitorEnterEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       
       if( id == 23 )
       {
          MonitorExitEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       
       if( id == 24 )
       {
          MonitorEnterEventGenInterleave.applyFromScalaEvent( buffer   );
       }
       else
       
       
       if( id == 25 )
       {
          MonitorExitEventGenInterleave.applyFromScalaEvent( buffer   );
       }
       else
       
       {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }




}