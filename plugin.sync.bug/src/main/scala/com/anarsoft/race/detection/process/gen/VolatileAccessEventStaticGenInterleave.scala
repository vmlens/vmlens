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


class VolatileAccessEventStaticGenInterleave (
  val threadId  : Long


,  val programCounter  : Int


,  val order  : Int


,  val fieldId  : Int


,  val methodCounter  : Int




,  var stackTraceOrdinal  : Int


,  var slidingWindowId  : Int
,  val methodId  : Int


,  val isWrite  : Boolean


,  val loopId  : Int


,  val runId  : Int


,  val runPosition  : Int




)    extends VolatileAccessEventStaticInterleave  
{
override def toString() = {
  var text =  "VolatileAccessEventStaticGenInterleave" 
  text = text + ", threadId:" +  threadId 
  text = text + ", programCounter:" +  programCounter 
  text = text + ", order:" +  order 
  text = text + ", fieldId:" +  fieldId 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", stackTraceOrdinal:" +  stackTraceOrdinal 
  text = text + ", slidingWindowId:" +  slidingWindowId 
  text = text + ", methodId:" +  methodId 
  text = text + ", isWrite:" +  isWrite 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 

text;

}



   
    
   



def accept(visitor : SyncActionsVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: VolatileAccessEventStaticGenInterleave => 
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


object  VolatileAccessEventStaticGenInterleave 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new VolatileAccessEventStaticGenInterleave (
     
           
            
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
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new VolatileAccessEventStaticGenInterleave (
     
            data.getLong()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  if( data.get( ) == 1.asInstanceOf[Byte] ) { true } else { false } 
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigVolatileAccessEventStaticGenInterleave extends Comparator[VolatileAccessEventStaticGenInterleave]
{
    def	compare(o1 :  VolatileAccessEventStaticGenInterleave,  o2 : VolatileAccessEventStaticGenInterleave ) =
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
          
        
        
          if( o1.isWrite != o2.isWrite )
          {
             java.lang.Boolean.compare( o1.isWrite , o2.isWrite  )
          }
          else
          
        
        
          if( o1.methodId != o2.methodId )
          {
             java.lang.Integer.compare( o1.methodId , o2.methodId  )
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


class SortVolatileAccessEventStaticGenInterleave extends Comparator[VolatileAccessEventStaticGenInterleave]
{
    def	compare(o1 :  VolatileAccessEventStaticGenInterleave,  o2 : VolatileAccessEventStaticGenInterleave ) =
    {
        
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
          
        
        
          if( o1.isWrite != o2.isWrite )
          {
             java.lang.Boolean.compare( o1.isWrite , o2.isWrite  )
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




