package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.scheduler._

import java.nio.ByteBuffer
import java.util.Comparator;


class MethodAtomicExitEventGen(
                                val threadId: Long


                                , val methodId: Int


                                , val methodCounter: Int


,  val hasCallback  : Byte


,  val loopId  : Int


,  val runId  : Int


,  val runPosition  : Int




)    extends MethodAtomicExitEvent  
{
override def toString() = {
  var text =  "MethodAtomicExitEventGen" 
  text = text + ", threadId:" +  threadId 
  text = text + ", methodId:" +  methodId 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", hasCallback:" +  hasCallback 
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
      case that: MethodAtomicExitEventGen => 
        {
            
             if( threadId != that.threadId )
             {
               false;
             }
             else
            
             if( methodId != that.methodId )
             {
               false;
             }
             else
            
             if( methodCounter != that.methodCounter )
             {
               false;
             }
             else
            
             if( hasCallback != that.hasCallback )
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


object  MethodAtomicExitEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new MethodAtomicExitEventGen (
     
           
            
                data.getLong()
           
          , 
            
                data.getInt()
           
          , 
            
                data.getInt()
           
          , 
            
                data.get()
           
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
     val result = new MethodAtomicExitEventGen (
     
            data.getLong()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.get()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigMethodAtomicExitEventGen extends Comparator[MethodAtomicExitEventGen]
{
    def	compare(o1 :  MethodAtomicExitEventGen,  o2 : MethodAtomicExitEventGen ) =
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


class SortMethodAtomicExitEventGen extends Comparator[MethodAtomicExitEventGen]
{
    def	compare(o1 :  MethodAtomicExitEventGen,  o2 : MethodAtomicExitEventGen ) =
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




