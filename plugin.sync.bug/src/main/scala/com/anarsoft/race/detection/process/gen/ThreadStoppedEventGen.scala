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

class ThreadStoppedEventGen (
  val threadId  : Long
,  val stoppedThreadId  : Long
,  val programCounter  : Int
,  val methodCounter  : Int
)    extends SyncAction  
{
override def toString() = {
  var text =  "ThreadStoppedEventGen" 
text = text + ", threadId:" +  threadId 
text = text + ", stoppedThreadId:" +  stoppedThreadId 
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
      case that: ThreadStoppedEventGen => 
        {
           if( threadId != that.threadId )
             {
               false;
             }
             else
           if( stoppedThreadId != that.stoppedThreadId )
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


object  ThreadStoppedEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new ThreadStoppedEventGen (
     
        
            
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
     val result = new ThreadStoppedEventGen (
     
         data.getLong()
        ,  data.getLong()
        ,  data.getInt()
        ,  data.getInt()
        );
     
     
     
     
     result;
   
   }
   


}










class SortOrigThreadStoppedEventGen extends Comparator[ThreadStoppedEventGen]
{
    def	compare(o1 :  ThreadStoppedEventGen,  o2 : ThreadStoppedEventGen ) =
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
          
        
        if( o1.stoppedThreadId != o2.stoppedThreadId )
          {
             java.lang.Long.compare( o1.stoppedThreadId , o2.stoppedThreadId  )
          }
          else
          
        
        {
            0;
          }
    
    
    }
    

}


class SortThreadStoppedEventGen extends Comparator[ThreadStoppedEventGen]
{
    def	compare(o1 :  ThreadStoppedEventGen,  o2 : ThreadStoppedEventGen ) =
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
          
        
        if( o1.stoppedThreadId != o2.stoppedThreadId )
          {
             java.lang.Long.compare( o1.stoppedThreadId , o2.stoppedThreadId  )
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




