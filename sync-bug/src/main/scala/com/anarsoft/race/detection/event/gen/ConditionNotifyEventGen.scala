package com.anarsoft.race.detection.event.gen;

import java.io.DataInputStream;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class ConditionNotifyEventGen (
   val threadIndex  : Int  
 ,  val methodCounter  : Int  
 ,  val objectHashCode  : Long  
 ,  val bytecodePosition  : Int  
 ,  val methodId  : Int  
 ,  val conditionNotifyEventType  : Int  
 ,  val loopId  : Int  
 ,  val runId  : Int  
 ,  val runPosition  : Int  
)    extends ConditionNotifyEvent  
{
override def toString : String = {
  var text =  "ConditionNotifyEventGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", objectHashCode:" +  objectHashCode 
  text = text + ", bytecodePosition:" +  bytecodePosition 
  text = text + ", methodId:" +  methodId 
  text = text + ", conditionNotifyEventType:" +  conditionNotifyEventType 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: ConditionNotifyEventGen => 
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
             if( objectHashCode != that.objectHashCode )
             {
               false;
             }
             else
             if( bytecodePosition != that.bytecodePosition )
             {
               false;
             }
             else
             if( methodId != that.methodId )
             {
               false;
             }
             else
             if( conditionNotifyEventType != that.conditionNotifyEventType )
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
             if( runPosition != that.runPosition )
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


object  ConditionNotifyEventGen 
{
   def applyFromJavaEvent(data : DataInputStream) =
   {
     val result = new ConditionNotifyEventGen (
          
            data.readInt()
          ,
            data.readInt()
          ,
            data.readLong()
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
          ,
            data.readInt()
     );
     result;
   }

}