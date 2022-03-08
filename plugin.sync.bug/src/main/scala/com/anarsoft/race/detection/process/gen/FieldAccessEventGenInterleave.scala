package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.nonVolatileField._

import java.nio.ByteBuffer
import java.util.Comparator;


class FieldAccessEventGenInterleave (
  val threadId  : Long


,  val programCounter  : Int


,  val fieldId  : Int


,  val methodCounter  : Int


,  val operation  : Int


,  val methodId  : Int


,  val stackTraceIncomplete  : Boolean




,  var stackTraceOrdinal  : Int
,  val objectHashCode  : Long




,  var slidingWindowId  : Int
,  val showSharedMemory  : Boolean


,  val loopId  : Int


,  val runId  : Int


,  val runPosition  : Int




)    extends NonVolatileFieldAccessEventInterleave with ApplyFieldEventVisitor 
{
override def toString() = {
  var text =  "FieldAccessEventGenInterleave" 
  text = text + ", threadId:" +  threadId 
  text = text + ", programCounter:" +  programCounter 
  text = text + ", fieldId:" +  fieldId 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", operation:" +  operation 
  text = text + ", methodId:" +  methodId 
  text = text + ", stackTraceIncomplete:" +  stackTraceIncomplete 
  text = text + ", stackTraceOrdinal:" +  stackTraceOrdinal 
  text = text + ", objectHashCode:" +  objectHashCode 
  text = text + ", slidingWindowId:" +  slidingWindowId 
  text = text + ", showSharedMemory:" +  showSharedMemory 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 

text;

}



   
    
   



def accept(visitor : FieldVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: FieldAccessEventGenInterleave => 
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
            
             if( stackTraceIncomplete != that.stackTraceIncomplete )
             {
               false;
             }
             else
            
             if( stackTraceOrdinal != that.stackTraceOrdinal )
             {
               false;
             }
             else
            
             if( objectHashCode != that.objectHashCode )
             {
               false;
             }
             else
            
             if( slidingWindowId != that.slidingWindowId )
             {
               false;
             }
             else
            
             if( showSharedMemory != that.showSharedMemory )
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


object  FieldAccessEventGenInterleave 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new FieldAccessEventGenInterleave (
     
           
            
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
           
          , 
            
                if( data.get( ) == 1.asInstanceOf[Byte] ) { true } else { false } 
           
          , 
            
                0
           
          , 
            
                data.getLong()
           
          , 
            
                0
           
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
     val result = new FieldAccessEventGenInterleave (
     
            data.getLong()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  if( data.get( ) == 1.asInstanceOf[Byte] ) { true } else { false } 
          ,  data.getInt()
          ,  data.getLong()
          ,  data.getInt()
          ,  if( data.get( ) == 1.asInstanceOf[Byte] ) { true } else { false } 
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigFieldAccessEventGenInterleave extends Comparator[FieldAccessEventGenInterleave]
{
    def	compare(o1 :  FieldAccessEventGenInterleave,  o2 : FieldAccessEventGenInterleave ) =
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
          
        
        
          if( o1.stackTraceIncomplete != o2.stackTraceIncomplete )
          {
             java.lang.Boolean.compare( o1.stackTraceIncomplete , o2.stackTraceIncomplete  )
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


class SortFieldAccessEventGenInterleave extends Comparator[FieldAccessEventGenInterleave]
{
    def	compare(o1 :  FieldAccessEventGenInterleave,  o2 : FieldAccessEventGenInterleave ) =
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
          
        
        
          if( o1.stackTraceIncomplete != o2.stackTraceIncomplete )
          {
             java.lang.Boolean.compare( o1.stackTraceIncomplete , o2.stackTraceIncomplete  )
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




