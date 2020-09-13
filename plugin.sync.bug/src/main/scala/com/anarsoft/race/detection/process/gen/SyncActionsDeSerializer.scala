package com.anarsoft.race.detection.process.gen;

import java.nio.ByteBuffer
import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.nonVolatileField.ApplyFieldEventVisitor
import com.anarsoft.race.detection.process.syncAction.SyncAction
import com.anarsoft.race.detection.process.method.ApplyMethodEventVisitor
import com.anarsoft.race.detection.process.monitor.MonitorEvent
import com.anarsoft.race.detection.process.directMemory._;
import com.anarsoft.race.detection.process.scheduler._

class SyncActionsDeSerializer   extends ReadStrategy[SyncAction]
{
   def eventArraySize() = 57
     
     val blockSize =  57 * 10000;
  

    def deSerializeJavaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();
       
       if( id == 7 )
       {
          VolatileAccessEventStaticGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 8 )
       {
          VolatileAccessEventStaticGenInterleave.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 9 )
       {
          VolatileAccessEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 10 )
       {
          VolatileAccessEventGenInterleave.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 11 )
       {
          VolatileArrayAccessEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 12 )
       {
          VolatileArrayAccessEventGenInterleave.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 14 )
       {
          LockEnterEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 15 )
       {
          LockEnterEventGenInterleave.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 16 )
       {
          LockExitEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 17 )
       {
          LockExitEventGenInterleave.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 18 )
       {
          StampedLockEnterEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 19 )
       {
          StampedLockEnterEventGenInterleave.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 20 )
       {
          StampedLockExitEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 21 )
       {
          StampedLockExitEventGenInterleave.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 34 )
       {
          ThreadBeginEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       if( id == 35 )
       {
          ThreadStoppedEventGen.applyFromJavaEvent( buffer   );
       }
       else
       
       {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }
    
    
    def deSerializeScalaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();
       
       if( id == 7 )
       {
          VolatileAccessEventStaticGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 8 )
       {
          VolatileAccessEventStaticGenInterleave.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 9 )
       {
          VolatileAccessEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 10 )
       {
          VolatileAccessEventGenInterleave.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 11 )
       {
          VolatileArrayAccessEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 12 )
       {
          VolatileArrayAccessEventGenInterleave.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 14 )
       {
          LockEnterEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 15 )
       {
          LockEnterEventGenInterleave.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 16 )
       {
          LockExitEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 17 )
       {
          LockExitEventGenInterleave.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 18 )
       {
          StampedLockEnterEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 19 )
       {
          StampedLockEnterEventGenInterleave.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 20 )
       {
          StampedLockExitEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 21 )
       {
          StampedLockExitEventGenInterleave.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 34 )
       {
          ThreadBeginEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       if( id == 35 )
       {
          ThreadStoppedEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }




}