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


class ArrayAccessEventGen(
                           val threadId: Long


                           , val programCounter: Int


                           , val fieldId: Int


                           , val methodCounter: Int


                           , val operation: Int


                           , val methodId: Int


,  val stackTraceIncomplete  : Boolean




,  var stackTraceOrdinal  : Int
,  val objectHashCode  : Long


,  val position  : Int


,  val classId  : Int




,  var slidingWindowId  : Int


)    extends ArrayAccessEvent  with ApplyFieldEventVisitor 
{
override def toString() = {
  var text =  "ArrayAccessEventGen" 
  text = text + ", threadId:" +  threadId 
  text = text + ", programCounter:" +  programCounter 
  text = text + ", fieldId:" +  fieldId 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", operation:" +  operation 
  text = text + ", methodId:" +  methodId 
  text = text + ", stackTraceIncomplete:" +  stackTraceIncomplete 
  text = text + ", stackTraceOrdinal:" +  stackTraceOrdinal 
  text = text + ", objectHashCode:" +  objectHashCode 
  text = text + ", position:" +  position 
  text = text + ", classId:" +  classId 
  text = text + ", slidingWindowId:" +  slidingWindowId 

text;

}



   
    
   



def accept(visitor : FieldVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: ArrayAccessEventGen => 
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
            
             if( position != that.position )
             {
               false;
             }
             else
            
             if( classId != that.classId )
             {
               false;
             }
             else
            
             if( slidingWindowId != that.slidingWindowId )
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
            
                data.getInt()
           
          , 
            
                data.getInt()
           
          , 
            
                0
           
     
     
     
     
     );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new ArrayAccessEventGen (
     
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
          ,  data.getInt()
          ,  data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigArrayAccessEventGen extends Comparator[ArrayAccessEventGen]
{
    def	compare(o1 :  ArrayAccessEventGen,  o2 : ArrayAccessEventGen ) =
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
          
        
        
          if( o1.position != o2.position )
          {
             java.lang.Integer.compare( o1.position , o2.position  )
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


class SortArrayAccessEventGen extends Comparator[ArrayAccessEventGen]
{
    def	compare(o1 :  ArrayAccessEventGen,  o2 : ArrayAccessEventGen ) =
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
          
        
        
          if( o1.position != o2.position )
          {
             java.lang.Integer.compare( o1.position , o2.position  )
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




