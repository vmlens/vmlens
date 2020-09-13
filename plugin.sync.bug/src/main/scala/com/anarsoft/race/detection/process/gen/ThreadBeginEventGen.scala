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


class ThreadBeginEventGen (
  val threadId  : Long
,  val startedThreadId  : Long
,  val programCounter  : Int
,  val methodCounter  : Int
)    extends SyncAction 
{
override def toString() = {
  var text =  "ThreadBeginEventGen" 
text = text + ", threadId:" +  threadId 
text = text + ", startedThreadId:" +  startedThreadId 
text = text + ", programCounter:" +  programCounter 
text = text + ", methodCounter:" +  methodCounter 
text;

}



   
    
   



def accept(visitor : SyncActionsVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: ThreadBeginEventGen => 
        {
           if( threadId != that.threadId )
             {
               false;
             }
             else
           if( startedThreadId != that.startedThreadId )
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
           true;
        }


      case _ => false
    }
  }



}


object  ThreadBeginEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new ThreadBeginEventGen (
     
        
            
            data.getLong()
            , 
            
            data.getLong()
            , 
            
            data.getInt()
            , 
            
            data.getInt()
            );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new ThreadBeginEventGen (
     
         data.getLong()
        ,  data.getLong()
        ,  data.getInt()
        ,  data.getInt()
        );
     
     
     
     
     result;
   
   }
   


}










class SortOrigThreadBeginEventGen extends Comparator[ThreadBeginEventGen]
{
    def	compare(o1 :  ThreadBeginEventGen,  o2 : ThreadBeginEventGen ) =
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
          
        
        if( o1.startedThreadId != o2.startedThreadId )
          {
             java.lang.Long.compare( o1.startedThreadId , o2.startedThreadId  )
          }
          else
          
        
        {
            0;
          }
    
    
    }
    

}


class SortThreadBeginEventGen extends Comparator[ThreadBeginEventGen]
{
    def	compare(o1 :  ThreadBeginEventGen,  o2 : ThreadBeginEventGen ) =
    {
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
          
        
        if( o1.startedThreadId != o2.startedThreadId )
          {
             java.lang.Long.compare( o1.startedThreadId , o2.startedThreadId  )
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




