package com.anarsoft.race.detection.process.gen;

import java.nio.ByteBuffer
import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.nonVolatileField.ApplyFieldEventVisitor
import com.anarsoft.race.detection.process.syncAction.SyncAction
import com.anarsoft.race.detection.process.method.ApplyMethodEventVisitor
import com.anarsoft.race.detection.process.monitor.MonitorEvent
import com.anarsoft.race.detection.process.directMemory._;
import com.anarsoft.race.detection.process.scheduler._

class SchedulerDeSerializer   extends ReadStrategy[SchedulerEvent]
{
   def eventArraySize() = 30
     
     val blockSize =  30 * 10000;
  

    def deSerializeJavaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();
       
       if( id == 36 )
       {
          MethodAtomicEnterEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 37 )
       {
          MethodAtomicExitEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 38 )
       {
          MethodCallbackEnterEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 39 )
       {
          MethodCallbackExitEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 40 )
       {
          LoopStartEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 41 )
       {
          LoopEndEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 42 )
       {
          RunStartEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 43 )
       {
          RunEndEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 44 )
       {
          LoopWarningEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }
    
    
    def deSerializeScalaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();
       
       if( id == 36 )
       {
          MethodAtomicEnterEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 37 )
       {
          MethodAtomicExitEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 38 )
       {
          MethodCallbackEnterEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 39 )
       {
          MethodCallbackExitEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 40 )
       {
          LoopStartEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 41 )
       {
          LoopEndEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 42 )
       {
          RunStartEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 43 )
       {
          RunEndEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 44 )
       {
          LoopWarningEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }




}