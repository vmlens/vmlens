package com.anarsoft.race.detection.event.gen;

import java.nio.ByteBuffer;

import com.anarsoft.race.detection.event.method._
import com.anarsoft.race.detection.event.control._;
import com.anarsoft.race.detection.event.nonvolatile._;
import com.anarsoft.race.detection.event.directmemory._;
import com.anarsoft.race.detection.event.interleave._;


class VolatileFieldAccessEventStaticGen (
   val threadIndex  : Int  
 ,  val bytecodePosition  : Int  
 ,  val fieldId  : Int  
 ,  val methodCounter  : Int  
 ,  val methodId  : Int  
 ,  val isWrite  : Boolean  
 ,  val loopId  : Int  
 ,  val runId  : Int  
 ,  val runPosition  : Int  
)    extends VolatileFieldAccessEventStatic  
{
override def toString() = {
  var text =  "VolatileFieldAccessEventStaticGen" 
  text = text + ", threadIndex:" +  threadIndex 
  text = text + ", bytecodePosition:" +  bytecodePosition 
  text = text + ", fieldId:" +  fieldId 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", methodId:" +  methodId 
  text = text + ", isWrite:" +  isWrite 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 
 text;
}

override def equals(other: Any) = {
    other match {
      case that: VolatileFieldAccessEventStaticGen => 
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
             if( methodId != that.methodId )
             {
               false;
             }
             else
             if( isWrite != that.isWrite )
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


object  VolatileFieldAccessEventStaticGen 
{
   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new VolatileFieldAccessEventStaticGen (
          
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
                if( data.get( ) == 1.asInstanceOf[Byte] ) { true } else { false } 
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