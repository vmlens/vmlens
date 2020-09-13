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


class VolatileDirectMemoryEventGen (
  val threadId  : Long
,  val programCounter  : Int
,  val methodCounter  : Int
,  val objectHashCode  : Long
,  var stackTraceOrdinal  : Int
,  val operation  : Int
,  val order  : Int
)    extends DirectMemoryEvent  
{
override def toString() = {
  var text =  "VolatileDirectMemoryEventGen" 
text = text + ", threadId:" +  threadId 
text = text + ", programCounter:" +  programCounter 
text = text + ", methodCounter:" +  methodCounter 
text = text + ", objectHashCode:" +  objectHashCode 
text = text + ", stackTraceOrdinal:" +  stackTraceOrdinal 
text = text + ", operation:" +  operation 
text = text + ", order:" +  order 
text;

}



   
    
   



def accept(visitor : DirectMemoryVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: VolatileDirectMemoryEventGen => 
        {
           if( threadId != that.threadId )
             {
               false;
             }
             else
           if( programCounter != that.programCounter )
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
           if( stackTraceOrdinal != that.stackTraceOrdinal )
             {
               false;
             }
             else
           if( operation != that.operation )
             {
               false;
             }
             else
           if( order != that.order )
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


object  VolatileDirectMemoryEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new VolatileDirectMemoryEventGen (
     
        
            
            data.getLong()
            , 
            
            data.getInt()
            , 
            
            data.getInt()
            , 
            
            data.getLong()
            , 
            
            0
            , 
            
            data.getInt()
            , 
            
            data.getInt()
            );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new VolatileDirectMemoryEventGen (
     
         data.getLong()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getLong()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        );
     
     
     
     
     result;
   
   }
   


}










class SortOrigVolatileDirectMemoryEventGen extends Comparator[VolatileDirectMemoryEventGen]
{
    def	compare(o1 :  VolatileDirectMemoryEventGen,  o2 : VolatileDirectMemoryEventGen ) =
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
          
        
        if( o1.programCounter != o2.programCounter )
          {
             java.lang.Integer.compare( o1.programCounter , o2.programCounter  )
          }
          else
          
        
        if( o1.order != o2.order )
          {
             java.lang.Integer.compare( o1.order , o2.order  )
          }
          else
          
        
        if( o1.operation != o2.operation )
          {
             java.lang.Integer.compare( o1.operation , o2.operation  )
          }
          else
          
        
        if( o1.objectHashCode != o2.objectHashCode )
          {
             java.lang.Long.compare( o1.objectHashCode , o2.objectHashCode  )
          }
          else
          
        
        if( o1.stackTraceOrdinal != o2.stackTraceOrdinal )
          {
             java.lang.Integer.compare( o1.stackTraceOrdinal , o2.stackTraceOrdinal  )
          }
          else
          
        
        {
            0;
          }
    
    
    }
    

}


class SortVolatileDirectMemoryEventGen extends Comparator[VolatileDirectMemoryEventGen]
{
    def	compare(o1 :  VolatileDirectMemoryEventGen,  o2 : VolatileDirectMemoryEventGen ) =
    {
        if( o1.objectHashCode != o2.objectHashCode )
          {
             java.lang.Long.compare( o1.objectHashCode , o2.objectHashCode  )
          }
          else
          
        
        if( o1.order != o2.order )
          {
             java.lang.Integer.compare( o1.order , o2.order  )
          }
          else
          
        
        if( o1.threadId != o2.threadId )
          {
             java.lang.Long.compare( o1.threadId , o2.threadId  )
          }
          else
          
        
        if( o1.programCounter != o2.programCounter )
          {
             java.lang.Integer.compare( o1.programCounter , o2.programCounter  )
          }
          else
          
        
        if( o1.operation != o2.operation )
          {
             java.lang.Integer.compare( o1.operation , o2.operation  )
          }
          else
          
        
        if( o1.stackTraceOrdinal != o2.stackTraceOrdinal )
          {
             java.lang.Integer.compare( o1.stackTraceOrdinal , o2.stackTraceOrdinal  )
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




