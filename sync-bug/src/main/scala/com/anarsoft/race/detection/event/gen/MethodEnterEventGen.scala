package com.anarsoft.race.detection.event.gen;

import java.io.DataInputStream;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class MethodEnterEventGen (
   val threadIndex  : Int  
 ,  val methodId  : Int  
 ,  val methodCounter  : Int  
 ,  val loopId  : Int  
 ,  val runId  : Int  
)    extends MethodEnterEvent  
{
override def toString : String = {
  var text =  "MethodEnterEventGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", methodId:" +  methodId 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: MethodEnterEventGen => 
        {
             if( threadIndex != that.threadIndex )
             {
               false;
             }
             else
             if( methodId != that.methodId )
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


object  MethodEnterEventGen 
{
   def applyFromJavaEvent(data : DataInputStream) =
   {
     val result = new MethodEnterEventGen (
          
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