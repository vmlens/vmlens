package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class VolatileArrayAccessEventGen (
   val threadIndex  : Int  
 ,  val bytecodePosition  : Int  
 ,  val arrayIndex  : Int  
 ,  val methodCounter  : Int  
 ,  val methodId  : Int  
 ,  val operation  : Int  
 ,  val objectHashCode  : Long  
 ,  val loopId  : Int  
 ,  val runId  : Int  
 ,  val runPosition  : Int  
)    extends VolatileArrayAccessEvent
{
override def toString : String = {
  var text =  "VolatileArrayAccessEventGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", bytecodePosition:" +  bytecodePosition 
  text = text + ", arrayIndex:" +  arrayIndex 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", methodId:" +  methodId 
  text = text + ", operation:" +  operation 
  text = text + ", objectHashCode:" +  objectHashCode 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: VolatileArrayAccessEventGen => 
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
             if( arrayIndex != that.arrayIndex )
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


object  VolatileArrayAccessEventGen 
{
   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new VolatileArrayAccessEventGen (
          
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
          ,
            data.getLong()
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