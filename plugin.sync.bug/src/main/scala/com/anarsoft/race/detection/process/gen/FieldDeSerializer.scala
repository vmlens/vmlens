package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.nonVolatileField.ApplyFieldEventVisitor
import com.anarsoft.race.detection.process.read._

import java.nio.ByteBuffer

class FieldDeSerializer extends ReadStrategy[ApplyFieldEventVisitor] {
  def eventArraySize() = 59

  val blockSize = 59 * 10000;


  def deSerializeJavaEvent(buffer: ByteBuffer) = {

    val id = buffer.get();


    if (id == 1) {
      FieldAccessEventGen.applyFromJavaEvent(buffer);
    }
    else if (id == 2) {
      FieldAccessEventGenInterleave.applyFromJavaEvent(buffer);
    }
    else if (id == 4) {
      FieldAccessEventStaticGen.applyFromJavaEvent(buffer);
    }
    else if (id == 5) {
      ArrayAccessEventGen.applyFromJavaEvent(buffer);
    }
       else
       
       {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }
    
    
    def deSerializeScalaEvent(buffer : ByteBuffer) =
    {
        
       val id = buffer.get();
       
       
       if( id == 1 )
       {
          FieldAccessEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       
       if( id == 2 )
       {
          FieldAccessEventGenInterleave.applyFromScalaEvent( buffer   );
       }
       else
       
       
       if( id == 4 ) {
         FieldAccessEventStaticGen.applyFromScalaEvent(buffer);
       }
       else
       
       
       if( id == 5 )
       {
          ArrayAccessEventGen.applyFromScalaEvent( buffer   );
       }
       else
       
       {
         throw new RuntimeException("id " + id + " could not be deserialized");
       }
       
       
    }




}