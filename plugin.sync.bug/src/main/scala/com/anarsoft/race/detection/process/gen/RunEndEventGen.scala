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


class RunEndEventGen (
  val loopId  : Int


,  val runId  : Int




)    extends RunEndEvent  
{
override def toString() = {
  var text =  "RunEndEventGen" 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 

text;

}



   
    
   



def accept(visitor : SchedulerVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: RunEndEventGen => 
        {
            
             if( loopId != that.loopId )
             {
               false;
             }
             else
            
             if( runId != that.runId )
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


object  RunEndEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new RunEndEventGen (
     
           
            
                data.getInt()
           
          , 
            
                data.getInt()
           
     
     
     
     
     );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new RunEndEventGen (
     
            data.getInt()
          ,  data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigRunEndEventGen extends Comparator[RunEndEventGen]
{
    def	compare(o1 :  RunEndEventGen,  o2 : RunEndEventGen ) =
    {
          {
            0;
          }
    
    
    }
    

}


class SortRunEndEventGen extends Comparator[RunEndEventGen]
{
    def	compare(o1 :  RunEndEventGen,  o2 : RunEndEventGen ) =
    {
          {
            0;
          }
    
    
    }
    

}




