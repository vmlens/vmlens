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


class MethodExitShortThreadIdEventGen(
                                       val shortThreadId: Short


                                       , val methodId: Int


                                       , val methodCounter: Int


                                     )    extends ApplyMethodEventVisitor
{
override def toString() = {
  var text =  "MethodExitShortThreadIdEventGen" 
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
      case that: MethodExitShortThreadIdEventGen => 
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


object  MethodExitShortThreadIdEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new MethodExitShortThreadIdEventGen (
     
           
            
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
     val result = new MethodExitShortThreadIdEventGen (
     
            data.getShort()
          ,  data.getInt()
          ,  data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigMethodExitShortThreadIdEventGen extends Comparator[MethodExitShortThreadIdEventGen]
{
    def	compare(o1 :  MethodExitShortThreadIdEventGen,  o2 : MethodExitShortThreadIdEventGen ) =
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


class SortMethodExitShortThreadIdEventGen extends Comparator[MethodExitShortThreadIdEventGen]
{
    def	compare(o1 :  MethodExitShortThreadIdEventGen,  o2 : MethodExitShortThreadIdEventGen ) =
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




