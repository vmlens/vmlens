package com.anarsoft.race.detection.event.gen;

import java.io.DataInputStream;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class FieldAccessEventGen (
   val threadIndex  : Int  
 ,  val fieldId  : Int  
 ,  val methodCounter  : Int  
 ,  val operation  : Int  
 ,  val methodId  : Int  
 ,  val objectHashCode  : Long  
 ,  val loopId  : Int  
 ,  val runId  : Int  
 ,  val runPosition  : Int  
)    extends NonVolatileFieldAccessEvent
{
override def toString : String = {
  var text =  "FieldAccessEventGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", fieldId:" +  fieldId 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", operation:" +  operation 
  text = text + ", methodId:" +  methodId 
  text = text + ", objectHashCode:" +  objectHashCode 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: FieldAccessEventGen => 
        {
             if( threadIndex != that.threadIndex )
             {
               false;
             }
             else
             if( fieldId != that.fieldId )
             {
               false;
             }
             else
             if( methodCounter != that.methodCounter )
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


object  FieldAccessEventGen 
{
   def applyFromJavaEvent(data : DataInputStream) =
   {
     val result = new FieldAccessEventGen (
          
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
            data.readLong()
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