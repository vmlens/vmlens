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

class MethodExitSmallThreadIdEventGen (
  val smallThreadId  : Byte
,  val methodId  : Int
,  val methodCounter  : Int
)    extends ApplyMethodEventVisitor 
{
override def toString() = {
  var text =  "MethodExitSmallThreadIdEventGen" 
text = text + ", smallThreadId:" +  smallThreadId 
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
      case that: MethodExitSmallThreadIdEventGen => 
        {
           if( smallThreadId != that.smallThreadId )
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


object  MethodExitSmallThreadIdEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new MethodExitSmallThreadIdEventGen (
     
        
            
            data.get()
            , 
            
            data.getInt()
            , 
            
            data.getInt()
            );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new MethodExitSmallThreadIdEventGen (
     
         data.get()
        ,  data.getInt()
        ,  data.getInt()
        );
     
     
     
     
     result;
   
   }
   


}










class SortOrigMethodExitSmallThreadIdEventGen extends Comparator[MethodExitSmallThreadIdEventGen]
{
    def	compare(o1 :  MethodExitSmallThreadIdEventGen,  o2 : MethodExitSmallThreadIdEventGen ) =
    {
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


class SortMethodExitSmallThreadIdEventGen extends Comparator[MethodExitSmallThreadIdEventGen]
{
    def	compare(o1 :  MethodExitSmallThreadIdEventGen,  o2 : MethodExitSmallThreadIdEventGen ) =
    {
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




