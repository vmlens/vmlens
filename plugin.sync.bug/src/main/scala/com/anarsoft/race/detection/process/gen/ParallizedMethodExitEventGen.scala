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

class ParallizedMethodExitEventGen (
  val threadId  : Long
,  val methodId  : Int
,  val methodCounter  : Int
)    extends ParallizedMethodExitEvent 
{
override def toString() = {
  var text =  "ParallizedMethodExitEventGen" 
text = text + ", threadId:" +  threadId 
text = text + ", methodId:" +  methodId 
text = text + ", methodCounter:" +  methodCounter 
text;

}



   
    
   



def accept(visitor : MethodVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: ParallizedMethodExitEventGen => 
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
           true;
        }


      case _ => false
    }
  }



}


object  ParallizedMethodExitEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new ParallizedMethodExitEventGen (
     
        
            
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
     val result = new ParallizedMethodExitEventGen (
     
         data.getLong()
        ,  data.getInt()
        ,  data.getInt()
        );
     
     
     
     
     result;
   
   }
   


}










class SortOrigParallizedMethodExitEventGen extends Comparator[ParallizedMethodExitEventGen]
{
    def	compare(o1 :  ParallizedMethodExitEventGen,  o2 : ParallizedMethodExitEventGen ) =
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


class SortParallizedMethodExitEventGen extends Comparator[ParallizedMethodExitEventGen]
{
    def	compare(o1 :  ParallizedMethodExitEventGen,  o2 : ParallizedMethodExitEventGen ) =
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




