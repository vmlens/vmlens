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


class LoopStartEventGen(
                         val loopId: Int


                       ) extends LoopStartEvent {
  override def toString() = {
    var text = "LoopStartEventGen"
    text = text + ", loopId:" + loopId

    text;

  }



   
    
   



def accept(visitor : SchedulerVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: LoopStartEventGen => 
        {
            
             if( loopId != that.loopId )
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


object  LoopStartEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new LoopStartEventGen (
     
           
            
                data.getInt()
           
     
     
     
     
     );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new LoopStartEventGen (
     
            data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigLoopStartEventGen extends Comparator[LoopStartEventGen]
{
    def	compare(o1 :  LoopStartEventGen,  o2 : LoopStartEventGen ) =
    {
          {
            0;
          }
    
    
    }
    

}


class SortLoopStartEventGen extends Comparator[LoopStartEventGen]
{
    def	compare(o1 :  LoopStartEventGen,  o2 : LoopStartEventGen ) =
    {
          {
            0;
          }
    
    
    }
    

}




