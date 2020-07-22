package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.method._
import com.anarsoft.race.detection.process.syncAction._;
import com.anarsoft.race.detection.process.volatileField._;
import com.anarsoft.race.detection.process.monitor._;
import com.anarsoft.race.detection.process.nonVolatileField._;
import java.util.Comparator
import java.nio.ByteBuffer;
import java.io.DataOutputStream;
import com.anarsoft.race.detection.process.directMemory._;
import com.anarsoft.race.detection.process.scheduler._
import com.anarsoft.race.detection.process.interleave._;
import com.anarsoft.race.detection.process.state._

class LoopEndEventGen (
  val loopId  : Int
,  val status  : Int
)    extends LoopEndEvent  
{
override def toString() = {
  var text =  "LoopEndEventGen" 
text = text + ", loopId:" +  loopId 
text = text + ", status:" +  status 
text;

}



   
    
   



def accept(visitor : SchedulerVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: LoopEndEventGen => 
        {
           if( loopId != that.loopId )
             {
               false;
             }
             else
           if( status != that.status )
             {
               false;
             }
             else
           true;
        }


      case _ => false
    }
  }



}


object  LoopEndEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new LoopEndEventGen (
     
        
            
            data.getInt()
            , 
            
            data.getInt()
            );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new LoopEndEventGen (
     
         data.getInt()
        ,  data.getInt()
        );
     
     
     
     
     result;
   
   }
   


}










class SortOrigLoopEndEventGen extends Comparator[LoopEndEventGen]
{
    def	compare(o1 :  LoopEndEventGen,  o2 : LoopEndEventGen ) =
    {
        {
            0;
          }
    
    
    }
    

}


class SortLoopEndEventGen extends Comparator[LoopEndEventGen]
{
    def	compare(o1 :  LoopEndEventGen,  o2 : LoopEndEventGen ) =
    {
        {
            0;
          }
    
    
    }
    

}




