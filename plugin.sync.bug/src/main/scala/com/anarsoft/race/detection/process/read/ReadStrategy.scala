package com.anarsoft.race.detection.process.read


import java.nio.ByteBuffer;


trait ReadStrategy[EVENT] {
  
 
 // def eventArraySize() : Int;
  
  
  def deSerializeJavaEvent(data : ByteBuffer) : EVENT; 
  def deSerializeScalaEvent(buffer : ByteBuffer) : EVENT;
  
 
  
  
}