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


class MethodEnterShortThreadIdEventGen (
  val shortThreadId  : Short
,  val methodId  : Int
,  val methodCounter  : Int
)    extends ApplyMethodEventVisitor  
{
override def toString() = {
  var text =  "MethodEnterShortThreadIdEventGen" 
text = text + ", shortThreadId:" +  shortThreadId 
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
      case that: MethodEnterShortThreadIdEventGen => 
        {
           if( shortThreadId != that.shortThreadId )
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


object  MethodEnterShortThreadIdEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new MethodEnterShortThreadIdEventGen (
     
        
            
            data.getShort()
            , 
            
            data.getInt()
            , 
            
            data.getInt()
            );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new MethodEnterShortThreadIdEventGen (
     
         data.getShort()
        ,  data.getInt()
        ,  data.getInt()
        );
     
     
     
     
     result;
   
   }
   


}










class SortOrigMethodEnterShortThreadIdEventGen extends Comparator[MethodEnterShortThreadIdEventGen]
{
    def	compare(o1 :  MethodEnterShortThreadIdEventGen,  o2 : MethodEnterShortThreadIdEventGen ) =
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


class SortMethodEnterShortThreadIdEventGen extends Comparator[MethodEnterShortThreadIdEventGen]
{
    def	compare(o1 :  MethodEnterShortThreadIdEventGen,  o2 : MethodEnterShortThreadIdEventGen ) =
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




