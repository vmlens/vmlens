package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.scheduler._

import java.nio.ByteBuffer
import java.util.Comparator;


class MethodCallbackExitEventGen (
  val threadId  : Long


,  val methodCounter  : Int


,  val loopId  : Int


,  val runId  : Int


,  val runPosition  : Int




)    extends MethodCallbackExitEvent  
{
override def toString() = {
  var text =  "MethodCallbackExitEventGen" 
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
      case that: MethodCallbackExitEventGen => 
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


object  MethodCallbackExitEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new MethodCallbackExitEventGen (
     
           
            
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
     val result = new MethodCallbackExitEventGen (
     
            data.getLong()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigMethodCallbackExitEventGen extends Comparator[MethodCallbackExitEventGen]
{
    def	compare(o1 :  MethodCallbackExitEventGen,  o2 : MethodCallbackExitEventGen ) =
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


class SortMethodCallbackExitEventGen extends Comparator[MethodCallbackExitEventGen]
{
    def	compare(o1 :  MethodCallbackExitEventGen,  o2 : MethodCallbackExitEventGen ) =
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




