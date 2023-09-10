package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.monitor.MonitorEvent
import com.anarsoft.race.detection.process.read._

import java.nio.ByteBuffer

class MonitorDeSerializer extends ReadStrategy[MonitorEvent] {
  def eventArraySize() = 45

  val blockSize = 45 * 10000;


  def deSerializeJavaEvent(buffer: ByteBuffer) = {

    val id = buffer.get();


    if (id == 14) {
      MonitorEnterEventGen.applyFromJavaEvent(buffer);
    }
    else if (id == 15) {
      MonitorExitEventGen.applyFromJavaEvent(buffer);
    }
    else {
      throw new RuntimeException("id " + id + " could not be deserialized");
    }
       
       
    }
    
    
    def deSerializeScalaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();


      if (id == 14) {
        MonitorEnterEventGen.applyFromScalaEvent(buffer);
      }
      else if (id == 15) {
        MonitorExitEventGen.applyFromScalaEvent(buffer);
      }
      else {
        throw new RuntimeException("id " + id + " could not be deserialized");
      }
       
       
    }




}