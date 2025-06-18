package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class ArrayAccessEventGen (
   val threadIndex  : Int  
 ,  val arrayIndex  : Int  
 ,  val methodCounter  : Int  
 ,  val objectHashCode  : Long  
 ,  val operation  : Int  
 ,  val methodId  : Int  
 ,  val loopId  : Int  
 ,  val runId  : Int  
 ,  val runPosition  : Int  
)    extends ArrayAccessEvent 
{
override def toString : String = {
  var text =  "ArrayAccessEventGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", arrayIndex:" +  arrayIndex 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", objectHashCode:" +  objectHashCode 
  text = text + ", operation:" +  operation 
  text = text + ", methodId:" +  methodId 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: ArrayAccessEventGen => 
        {
             if( threadIndex != that.threadIndex )
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
             if( objectHashCode != that.objectHashCode )
             {
               false;
             }
             else
             if( operation != that.operation )
             {
               false;
             }
             else
             if( methodId != that.methodId )
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


object  ArrayAccessEventGen 
{
   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new ArrayAccessEventGen (
          
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
          ,
            data.getInt()
     );
     result;
   }

}