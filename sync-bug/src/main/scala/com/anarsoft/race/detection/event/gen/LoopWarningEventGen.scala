package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class LoopWarningEventGen (
   val loopId  : Int  
 ,  val runId  : Int  
 ,  val messageId  : Int  
)    extends LoopWarningEvent  
{
override def toString : String = {
  var text =  "LoopWarningEventGen" 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", messageId:" +  messageId 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: LoopWarningEventGen => 
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
             if( messageId != that.messageId )
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


object  LoopWarningEventGen 
{
   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new LoopWarningEventGen (
          
                data.getInt()
          ,
                data.getInt()
          ,
                data.getInt()
     );
     result;
   }

}