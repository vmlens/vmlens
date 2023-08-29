package com.anarsoft.race.detection.process.gen;

import java.nio.ByteBuffer
import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.nonVolatileField.ApplyFieldEventVisitor
import com.anarsoft.race.detection.process.syncAction.SyncAction
import com.anarsoft.race.detection.process.method.ApplyMethodEventVisitor
import com.anarsoft.race.detection.process.monitor.MonitorEvent
import com.anarsoft.race.detection.process.directMemory._;
import com.anarsoft.race.detection.process.scheduler._

class MethodDeSerializer extends ReadStrategy[ApplyMethodEventVisitor] {
  def eventArraySize() = 21

  val blockSize = 21 * 10000;


  def deSerializeJavaEvent(buffer: ByteBuffer) = {

    val id = buffer.get();


    if (id == 26) {
      MethodEnterEventGen.applyFromJavaEvent(buffer);
    }
    else
       
       
       if( id == 27 )
       {
          MethodExitEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       
       if( id == 28 )
       {
          ParallizedMethodEnterEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       
       if( id == 29 )
       {
          ParallizedMethodExitEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       
       if( id == 30 )
       {
          MethodEnterSmallThreadIdEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       
       if( id == 31 )
       {
          MethodExitSmallThreadIdEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       
       if( id == 32 )
       {
          MethodEnterShortThreadIdEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       
       if( id == 33 )
       {
          MethodExitShortThreadIdEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }
    
    
    def deSerializeScalaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();
       
       
       if( id == 26 )
       {
          MethodEnterEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       
       if( id == 27 )
       {
          MethodExitEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       
       if( id == 28 )
       {
          ParallizedMethodEnterEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       
       if( id == 29 )
       {
          ParallizedMethodExitEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       
       if( id == 30 )
       {
          MethodEnterSmallThreadIdEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       
       if( id == 31 )
       {
          MethodExitSmallThreadIdEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       
       if( id == 32 )
       {
          MethodEnterShortThreadIdEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       
       if( id == 33 )
       {
          MethodExitShortThreadIdEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }




}