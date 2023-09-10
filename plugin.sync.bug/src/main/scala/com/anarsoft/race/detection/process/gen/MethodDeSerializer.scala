package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.method.ApplyMethodEventVisitor
import com.anarsoft.race.detection.process.read._

import java.nio.ByteBuffer

class MethodDeSerializer extends ReadStrategy[ApplyMethodEventVisitor] {
  val blockSize = 17 * 10000;

  def eventArraySize() = 17

  def deSerializeJavaEvent(buffer: ByteBuffer) = {

    val id = buffer.get();


    if (id == 16) {
      MethodEnterEventGen.applyFromJavaEvent(buffer);
    }
    else if (id == 17) {
      MethodExitEventGen.applyFromJavaEvent(buffer);
    }
    else {
      throw new RuntimeException("id " + id + " could not be deserialized");
    }
       
       
    }
    
    
    def deSerializeScalaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();


      if (id == 16) {
        MethodEnterEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 17) {
        MethodExitEventGen.applyFromScalaEvent(buffer);
      }
      else {
        throw new RuntimeException("id " + id + " could not be deserialized");
      }
       
       
    }




}