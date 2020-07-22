package com.anarsoft.race.detection.process.read


import scala.collection.mutable.ArrayBuffer;
import java.nio.ByteBuffer;
import com.anarsoft.race.detection.process.workflow.SingleStep

class StepReadEvents[EVENT,C]( val extract : (  C  ) =>  StreamAndStreamStatistic[EVENT]  , createCallBack : (  C ) => ReadCallback[EVENT]  ) extends SingleStep[C] {
  

  
  
   def execute(  in : C )
   {
     
     val stream  =   extract(in);
     
     if( stream != null)
     {
      stream.execute(createCallBack(in));
     }
       
     
     
   }
  
  
  
   
   
  
  
  
}