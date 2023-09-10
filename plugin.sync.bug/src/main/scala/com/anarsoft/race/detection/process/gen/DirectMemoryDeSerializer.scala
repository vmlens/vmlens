package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.directMemory._
import com.anarsoft.race.detection.process.read._

import java.nio.ByteBuffer

class DirectMemoryDeSerializer extends ReadStrategy[DirectMemoryEvent] {
  val blockSize = 45 * 10000;

  def eventArraySize() = 45

  def deSerializeJavaEvent(buffer: ByteBuffer) = {

    val id = buffer.get();


    if (id == 9) {
      VolatileDirectMemoryEventGen.applyFromJavaEvent(buffer);
    }
    else {
      throw new RuntimeException("id " + id + " could not be deserialized");
    }


  }


  def deSerializeScalaEvent(buffer: ByteBuffer) =
    {
        
       val id = buffer.get();


      if (id == 9) {
        VolatileDirectMemoryEventGen.applyFromScalaEvent(buffer);
      }
      else {
        throw new RuntimeException("id " + id + " could not be deserialized");
      }
       
       
    }




}