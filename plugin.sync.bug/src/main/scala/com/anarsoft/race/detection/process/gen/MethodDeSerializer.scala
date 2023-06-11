package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.method.ApplyMethodEventVisitor
import com.anarsoft.race.detection.process.read._

import java.nio.ByteBuffer

class MethodDeSerializer extends ReadStrategy[ApplyMethodEventVisitor] {
  val blockSize = 21 * 10000;

  def eventArraySize() = 21

  def deSerializeJavaEvent(buffer: ByteBuffer) = {
        
       val id = buffer.get();
       
       
       if( id == 26 )
       {
          MethodEnterEventGen.applyFromJavaEvent( buffer   );
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