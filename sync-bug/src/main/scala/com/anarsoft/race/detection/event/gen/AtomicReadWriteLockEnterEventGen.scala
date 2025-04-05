package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class AtomicReadWriteLockEnterEventGen (
   val threadIndex  : Int  
 ,  val methodCounter  : Int  
 ,  val objectHashCode  : Long  
 ,  val lockType  : Int  
 ,  val loopId  : Int  
 ,  val runId  : Int  
 ,  val runPosition  : Int  
 ,  val atomicMethodId  : Int  
)    extends AtomicReadWriteLockEnterEvent  
{
override def toString() = {
  var text =  "AtomicReadWriteLockEnterEventGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", objectHashCode:" +  objectHashCode 
  text = text + ", lockType:" +  lockType 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 
  text = text + ", atomicMethodId:" +  atomicMethodId 
 text;
}

override def equals(other: Any) = {
    other match {
      case that: AtomicReadWriteLockEnterEventGen => 
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
             if( atomicMethodId != that.atomicMethodId )
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


object  AtomicReadWriteLockEnterEventGen 
{
   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new AtomicReadWriteLockEnterEventGen (
          
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
     );
     result;
   }

}