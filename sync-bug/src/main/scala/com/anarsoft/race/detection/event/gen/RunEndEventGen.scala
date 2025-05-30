package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class RunEndEventGen (
   val loopId  : Int  
 ,  val runId  : Int  
)    extends RunEndEvent  
{
override def toString : String = {
  var text =  "RunEndEventGen" 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
 text;
}

override def equals(other: Any) : Boolean = {
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

}