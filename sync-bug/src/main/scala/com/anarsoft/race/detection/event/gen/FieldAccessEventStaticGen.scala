package com.anarsoft.race.detection.event.gen;

import java.io.DataInputStream;

import com.anarsoft.race.detection.event.automatictest._
import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class FieldAccessEventStaticGen (
   val threadIndex  : Int  
 ,  val fieldId  : Int  
 ,  val methodCounter  : Int  
 ,  val operation  : Int  
 ,  val methodId  : Int  
 ,  val loopId  : Int  
 ,  val runId  : Int  
 ,  val runPosition  : Int  
 ,  val dominatorTreeCounter  : Int  
)     extends NonVolatileFieldAccessEventStatic
{
override def toString : String = {
  var text =  "FieldAccessEventStaticGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", fieldId:" +  fieldId 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", operation:" +  operation 
  text = text + ", methodId:" +  methodId 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 
  text = text + ", dominatorTreeCounter:" +  dominatorTreeCounter 
 text;
}

override def equals(other: Any) : Boolean = {
    other match {
      case that: FieldAccessEventStaticGen => 
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


object  FieldAccessEventStaticGen 
{
   def applyFromJavaEvent(data : DataInputStream) =
   {
     val result = new FieldAccessEventStaticGen (
          
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