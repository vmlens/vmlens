package com.anarsoft.race.detection.process.gen;

import java.nio.ByteBuffer
import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.nonVolatileField.ApplyFieldEventVisitor
import com.anarsoft.race.detection.process.syncAction.SyncAction
import com.anarsoft.race.detection.process.method.ApplyMethodEventVisitor
import com.anarsoft.race.detection.process.monitor.MonitorEvent
import com.anarsoft.race.detection.process.directMemory._;
import com.anarsoft.race.detection.process.scheduler._
import com.anarsoft.race.detection.process.state._

class StateDeSerializer   extends ReadStrategy[StateEvent]
{
   def eventArraySize() = 37
     
     val blockSize =  37 * 10000;
  

    def deSerializeJavaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();
       
       if( id == 45 )
       {
          StateEventFieldGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 47 )
       {
          StateEventStaticFieldGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 49 )
       {
          StateEventArrayGen.applyFromJavaEvent( buffer   );
       }
       else
       
       {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }
    
    
    def deSerializeScalaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();
       
       if( id == 45 )
       {
          StateEventFieldGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 47 )
       {
          StateEventStaticFieldGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 49 )
       {
          StateEventArrayGen.applyFromScalaEvent( buffer   );
       }
       else
       
       {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }




}