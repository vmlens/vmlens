package com.anarsoft.race.detection.event.gen;

import java.io.DataInputStream;

import com.anarsoft.race.detection.event.automatictest._
import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class MonitorEnterEventGen (
   val threadIndex  : Int  
 ,  val methodCounter  : Int  
 ,  val objectHashCode  : Long  
 ,  val methodId  : Int  
 ,  val bytecodePosition  : Int  
 ,  val loopId  : Int  
 ,  val runId  : Int  
 ,  val runPosition  : Int  
 ,  val dominatorTreeCounter  : Int  
)    extends MonitorEnterEvent
{
override def toString : String = {
  var text =  "MonitorEnterEventGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", objectHashCode:" +  objectHashCode 
  text = text + ", methodId:" +  methodId 
  text = text + ", bytecodePosition:" +  bytecodePosition 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 
  text = text + ", dominatorTreeCounter:" +  dominatorTreeCounter 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: MonitorEnterEventGen => 
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
             if( methodId != that.methodId )
             {
               false;
             }
             else
             if( bytecodePosition != that.bytecodePosition )
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
             if( dominatorTreeCounter != that.dominatorTreeCounter )
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


object  MonitorEnterEventGen 
{
   def applyFromJavaEvent(data : DataInputStream) =
   {
     val result = new MonitorEnterEventGen (
          
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