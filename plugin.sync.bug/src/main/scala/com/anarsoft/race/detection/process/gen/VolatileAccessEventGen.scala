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


class VolatileAccessEventGen (
  val threadId  : Long


,  val programCounter  : Int


,  val order  : Int


,  val fieldId  : Int


,  val methodCounter  : Int




,  var stackTraceOrdinal  : Int


,  var slidingWindowId  : Int
,  val methodId  : Int


,  val operation  : Int


,  val objectHashCode  : Long




)    extends VolatileAccessEvent  
{
override def toString() = {
  var text =  "VolatileAccessEventGen" 
  text = text + ", threadId:" +  threadId 
  text = text + ", programCounter:" +  programCounter 
  text = text + ", order:" +  order 
  text = text + ", fieldId:" +  fieldId 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", stackTraceOrdinal:" +  stackTraceOrdinal 
  text = text + ", slidingWindowId:" +  slidingWindowId 
  text = text + ", methodId:" +  methodId 
  text = text + ", operation:" +  operation 
  text = text + ", objectHashCode:" +  objectHashCode 

text;

}



   
    
   



def accept(visitor : SyncActionsVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: VolatileAccessEventGen => 
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
            
             if( order != that.order )
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
            
             if( stackTraceOrdinal != that.stackTraceOrdinal )
             {
               false;
             }
             else
            
             if( slidingWindowId != that.slidingWindowId )
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
             true;
        }


      case _ => false
    }
  }



}


object  VolatileAccessEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new VolatileAccessEventGen (
     
           
            
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
            
                0
           
          , 
            
                0
           
          , 
            
                data.getInt()
           
          , 
            
                data.getInt()
           
          , 
            
                data.getLong()
           
     
     
     
     
     );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new VolatileAccessEventGen (
     
            data.getLong()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getLong()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigVolatileAccessEventGen extends Comparator[VolatileAccessEventGen]
{
    def	compare(o1 :  VolatileAccessEventGen,  o2 : VolatileAccessEventGen ) =
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
          
        
        
          if( o1.fieldId != o2.fieldId )
          {
             java.lang.Integer.compare( o1.fieldId , o2.fieldId  )
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
          
        
        
          if( o1.methodId != o2.methodId )
          {
             java.lang.Integer.compare( o1.methodId , o2.methodId  )
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


class SortVolatileAccessEventGen extends Comparator[VolatileAccessEventGen]
{
    def	compare(o1 :  VolatileAccessEventGen,  o2 : VolatileAccessEventGen ) =
    {
        
          if( o1.objectHashCode != o2.objectHashCode )
          {
             java.lang.Long.compare( o1.objectHashCode , o2.objectHashCode  )
          }
          else
          
        
        
          if( o1.fieldId != o2.fieldId )
          {
             java.lang.Integer.compare( o1.fieldId , o2.fieldId  )
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
          
        
        
          if( o1.methodId != o2.methodId )
          {
             java.lang.Integer.compare( o1.methodId , o2.methodId  )
          }
          else
          
        
          {
            0;
          }
    
    
    }
    

}




