package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class ConditionWaitExitEventGen (
   val threadIndex  : Int  
 ,  val methodCounter  : Int  
 ,  val objectHashCode  : Long  
 ,  val bytecodePosition  : Int  
 ,  val methodId  : Int  
 ,  val lockType  : Int  
 ,  val loopId  : Int  
 ,  val runId  : Int  
 ,  val runPosition  : Int  
)    extends ConditionWaitExitEvent  
{
override def toString : String = {
  var text =  "ConditionWaitExitEventGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", objectHashCode:" +  objectHashCode 
  text = text + ", bytecodePosition:" +  bytecodePosition 
  text = text + ", methodId:" +  methodId 
  text = text + ", lockType:" +  lockType 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: ConditionWaitExitEventGen => 
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
             if( lockType != that.lockType )
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


object  ConditionWaitExitEventGen 
{
   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new ConditionWaitExitEventGen (
          
            data.getInt()
          ,
            data.getInt()
          ,
            data.getLong()
          ,
            data.getInt()
          ,
            data.getInt()
          ,
            data.getInt()
          ,
            data.getInt()
          ,
            data.getInt()
          ,
            data.getInt()
     );
     result;
   }

}