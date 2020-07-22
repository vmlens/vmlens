package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.method._
import com.anarsoft.race.detection.process.syncAction._;
import com.anarsoft.race.detection.process.volatileField._;
import com.anarsoft.race.detection.process.monitor._;
import com.anarsoft.race.detection.process.nonVolatileField._;
import java.util.Comparator
import java.nio.ByteBuffer;
import java.io.DataOutputStream;
import com.anarsoft.race.detection.process.directMemory._;
import com.anarsoft.race.detection.process.scheduler._
import com.anarsoft.race.detection.process.interleave._;
import com.anarsoft.race.detection.process.state._

class MethodCallbackEnterEventGen (
  val threadId  : Long
,  val methodCounter  : Int
,  val loopId  : Int
,  val runId  : Int
,  val runPosition  : Int
)    extends MethodCallbackEnterEvent  
{
override def toString() = {
  var text =  "MethodCallbackEnterEventGen" 
text = text + ", threadId:" +  threadId 
text = text + ", methodCounter:" +  methodCounter 
text = text + ", loopId:" +  loopId 
text = text + ", runId:" +  runId 
text = text + ", runPosition:" +  runPosition 
text;

}



   
    
   



def accept(visitor : SchedulerVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: MethodCallbackEnterEventGen => 
        {
           if( threadId != that.threadId )
             {
               false;
             }
             else
           if( methodCounter != that.methodCounter )
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


object  MethodCallbackEnterEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new MethodCallbackEnterEventGen (
     
        
            
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
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new MethodCallbackEnterEventGen (
     
         data.getLong()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        );
     
     
     
     
     result;
   
   }
   


}










class SortOrigMethodCallbackEnterEventGen extends Comparator[MethodCallbackEnterEventGen]
{
    def	compare(o1 :  MethodCallbackEnterEventGen,  o2 : MethodCallbackEnterEventGen ) =
    {
        if( o1.threadId != o2.threadId )
          {
             java.lang.Long.compare( o1.threadId , o2.threadId  )
          }
          else
          
        
        if( o1.methodCounter != o2.methodCounter )
          {
             java.lang.Integer.compare( o1.methodCounter , o2.methodCounter  )
          }
          else
          
        
        {
            0;
          }
    
    
    }
    

}


class SortMethodCallbackEnterEventGen extends Comparator[MethodCallbackEnterEventGen]
{
    def	compare(o1 :  MethodCallbackEnterEventGen,  o2 : MethodCallbackEnterEventGen ) =
    {
        if( o1.threadId != o2.threadId )
          {
             java.lang.Long.compare( o1.threadId , o2.threadId  )
          }
          else
          
        
        if( o1.methodCounter != o2.methodCounter )
          {
             java.lang.Integer.compare( o1.methodCounter , o2.methodCounter  )
          }
          else
          
        
        {
            0;
          }
    
    
    }
    

}




