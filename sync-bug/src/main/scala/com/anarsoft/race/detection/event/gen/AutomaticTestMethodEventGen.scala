package com.anarsoft.race.detection.event.gen;

import java.io.DataInputStream;

import com.anarsoft.race.detection.event.automatictest._
import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class AutomaticTestMethodEventGen (
   val threadIndex  : Int  
 ,  val automaticTestType  : Int  
 ,  val automaticTestId  : Int  
 ,  val automaticTestMethodId  : Int  
 ,  val loopId  : Int  
 ,  val runId  : Int  
)    extends AutomaticTestEvent  
{
override def toString : String = {
  var text =  "AutomaticTestMethodEventGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", automaticTestType:" +  automaticTestType 
  text = text + ", automaticTestId:" +  automaticTestId 
  text = text + ", automaticTestMethodId:" +  automaticTestMethodId 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: AutomaticTestMethodEventGen => 
        {
             if( threadIndex != that.threadIndex )
             {
               false;
             }
             else
             if( automaticTestType != that.automaticTestType )
             {
               false;
             }
             else
             if( automaticTestId != that.automaticTestId )
             {
               false;
             }
             else
             if( automaticTestMethodId != that.automaticTestMethodId )
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


object  AutomaticTestMethodEventGen 
{
   def applyFromJavaEvent(data : DataInputStream) =
   {
     val result = new AutomaticTestMethodEventGen (
          
            data.readInt()
          ,
            data.readInt()
          ,
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