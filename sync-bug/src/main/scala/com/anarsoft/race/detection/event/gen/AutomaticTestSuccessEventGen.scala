package com.anarsoft.race.detection.event.gen;

import java.io.DataInputStream;

import com.anarsoft.race.detection.event.automatictest._
import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class AutomaticTestSuccessEventGen (
   val automaticTestId  : Int  
 ,  val loopId  : Int  
 ,  val runId  : Int  
)    extends AutomaticTestSuccessEvent  
{
override def toString : String = {
  var text =  "AutomaticTestSuccessEventGen" 
  text = text + ", automaticTestId:" +  automaticTestId 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: AutomaticTestSuccessEventGen => 
        {
             if( automaticTestId != that.automaticTestId )
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


object  AutomaticTestSuccessEventGen 
{
   def applyFromJavaEvent(data : DataInputStream) =
   {
     val result = new AutomaticTestSuccessEventGen (
          
            data.readInt()
          ,
            data.readInt()
          ,
            data.readInt()
     );
     result;
   }

}