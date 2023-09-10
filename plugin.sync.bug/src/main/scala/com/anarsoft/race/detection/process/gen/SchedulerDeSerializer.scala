package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.read._
import com.anarsoft.race.detection.process.scheduler._

import java.nio.ByteBuffer

class SchedulerDeSerializer   extends ReadStrategy[SchedulerEvent]
{
   def eventArraySize() = 30
     
     val blockSize =  30 * 10000;
  

    def deSerializeJavaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();
       
       
       if (id == 20) {
         MethodAtomicEnterEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 21) {
         MethodAtomicExitEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 22) {
         MethodCallbackEnterEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 23) {
         MethodCallbackExitEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 24) {
         LoopStartEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 25) {
         LoopEndEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 26) {
         RunStartEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 27) {
         RunEndEventGen.applyFromJavaEvent(buffer);
       }
       else if (id == 28) {
         LoopWarningEventGen.applyFromJavaEvent(buffer);
       }
       else {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }
    
    
    def deSerializeScalaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();


      if (id == 20) {
        MethodAtomicEnterEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 21) {
        MethodAtomicExitEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 22) {
        MethodCallbackEnterEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 23) {
        MethodCallbackExitEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 24) {
        LoopStartEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 25) {
        LoopEndEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 26) {
        RunStartEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 27) {
        RunEndEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 28) {
        LoopWarningEventGen.applyFromScalaEvent(buffer);
      }
      else {
        throw new RuntimeException("id " + id + " could not be deserialized");
      }
       
       
    }




}