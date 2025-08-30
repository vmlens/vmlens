package com.anarsoft.race.detection.event.gen;

import java.io.DataInputStream;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class MethodExitEventGen (
   val threadIndex  : Int  
 ,  val methodCounter  : Int  
 ,  val loopId  : Int  
 ,  val runId  : Int  
)    extends MethodExitEvent  
{
override def toString : String = {
  var text =  "MethodExitEventGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: MethodExitEventGen => 
        {
             if( threadIndex != that.threadIndex )
             {
               false;
             }
             else
             if( methodCounter != that.methodCounter )
             {
               false;
             }
             else
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


object  MethodExitEventGen 
{
   def applyFromJavaEvent(data : DataInputStream) =
   {
     val result = new MethodExitEventGen (
          
            data.readInt()
          ,
            data.readInt()
          ,
            data.readInt()
          ,
            data.readInt()
     );
     result;
   }

}