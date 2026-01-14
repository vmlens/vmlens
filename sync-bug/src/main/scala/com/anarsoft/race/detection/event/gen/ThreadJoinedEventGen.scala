package com.anarsoft.race.detection.event.gen;

import java.io.DataInputStream;

import com.anarsoft.race.detection.event.automatictest._
import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class ThreadJoinedEventGen (
   val threadIndex  : Int  
 ,  val bytecodePosition  : Int  
 ,  val methodId  : Int  
 ,  val joinedThreadIndex  : Int  
 ,  val methodCounter  : Int  
 ,  val eventType  : Int  
 ,  val loopId  : Int  
 ,  val runId  : Int  
 ,  val runPosition  : Int  
 ,  val dominatorTreeCounter  : Int  
)    extends ThreadJoinedEvent  
{
override def toString : String = {
  var text =  "ThreadJoinedEventGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", bytecodePosition:" +  bytecodePosition 
  text = text + ", methodId:" +  methodId 
  text = text + ", joinedThreadIndex:" +  joinedThreadIndex 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", eventType:" +  eventType 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 
  text = text + ", dominatorTreeCounter:" +  dominatorTreeCounter 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: ThreadJoinedEventGen => 
        {
             if( threadIndex != that.threadIndex )
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
             if( joinedThreadIndex != that.joinedThreadIndex )
             {
               false;
             }
             else
             if( methodCounter != that.methodCounter )
             {
               false;
             }
             else
             if( eventType != that.eventType )
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


object  ThreadJoinedEventGen 
{
   def applyFromJavaEvent(data : DataInputStream) =
   {
     val result = new ThreadJoinedEventGen (
          
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