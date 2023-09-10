package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.read._
import com.anarsoft.race.detection.process.syncAction.SyncAction

import java.nio.ByteBuffer

class SyncActionsDeSerializer   extends ReadStrategy[SyncAction]
{
   def eventArraySize() = 57
     
     val blockSize =  57 * 10000;
  

    def deSerializeJavaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();
       
       
       if (id == 6) {
         VolatileAccessEventStaticGen.applyFromJavaEvent(buffer);
       }
       else if (id == 7) {
         VolatileAccessEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 8) {
         VolatileArrayAccessEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 10) {
         LockEnterEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 11) {
         LockExitEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 12) {
         StampedLockEnterEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 13) {
         StampedLockExitEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 18) {
         ThreadBeginEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 19) {
         ThreadStoppedEventGen.applyFromJavaEvent(buffer);
       }
       else {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }
    
    
    def deSerializeScalaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();


      if (id == 6) {
        VolatileAccessEventStaticGen.applyFromScalaEvent(buffer);
      }
      else if (id == 7) {
        VolatileAccessEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 8) {
        VolatileArrayAccessEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 10) {
        LockEnterEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 11) {
        LockExitEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 12) {
        StampedLockEnterEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 13) {
        StampedLockExitEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 18) {
        ThreadBeginEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 19) {
        ThreadStoppedEventGen.applyFromScalaEvent(buffer);
      }
      else {
        throw new RuntimeException("id " + id + " could not be deserialized");
      }
       
       
    }




}