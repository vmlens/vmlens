package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class AtomicNonBlockingEventGen (
   val threadIndex  : Int  
 ,  val bytecodePosition  : Int  
 ,  val methodCounter  : Int  
 ,  val methodId  : Int  
 ,  val operation  : Int  
 ,  val objectHashCode  : Long  
 ,  val atomicMethodId  : Int  
 ,  val loopId  : Int  
 ,  val runId  : Int  
 ,  val runPosition  : Int  
)    extends AtomicNonBlockingEvent 
{
override def toString : String = {
  var text =  "AtomicNonBlockingEventGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", bytecodePosition:" +  bytecodePosition 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", methodId:" +  methodId 
  text = text + ", interleaveoperation:" +  operation
  text = text + ", objectHashCode:" +  objectHashCode 
  text = text + ", atomicMethodId:" +  atomicMethodId 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: AtomicNonBlockingEventGen => 
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
             if( methodCounter != that.methodCounter )
             {
               false;
             }
             else
             if( methodId != that.methodId )
             {
               false;
             }
             else
             if( operation != that.operation )
             {
               false;
             }
             else
             if( objectHashCode != that.objectHashCode )
             {
               false;
             }
             else
             if( atomicMethodId != that.atomicMethodId )
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


object  AtomicNonBlockingEventGen 
{
   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new AtomicNonBlockingEventGen (
          
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
            data.getLong()
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