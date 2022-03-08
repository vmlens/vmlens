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


class ParallizedMethodEnterEventGen (
  val threadId  : Long


,  val methodId  : Int


,  val methodCounter  : Int


,  val parallizeId  : Int




)    extends ParallizedMethodEnterEvent  
{
override def toString() = {
  var text =  "ParallizedMethodEnterEventGen" 
  text = text + ", threadId:" +  threadId 
  text = text + ", methodId:" +  methodId 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", parallizeId:" +  parallizeId 

text;

}



   
    
   



def accept(visitor : MethodVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: ParallizedMethodEnterEventGen => 
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
            
             if( parallizeId != that.parallizeId )
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


object  ParallizedMethodEnterEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new ParallizedMethodEnterEventGen (
     
           
            
                data.getLong()
           
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
     val result = new ParallizedMethodEnterEventGen (
     
            data.getLong()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigParallizedMethodEnterEventGen extends Comparator[ParallizedMethodEnterEventGen]
{
    def	compare(o1 :  ParallizedMethodEnterEventGen,  o2 : ParallizedMethodEnterEventGen ) =
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
          
        
        
          if( o1.parallizeId != o2.parallizeId )
          {
             java.lang.Integer.compare( o1.parallizeId , o2.parallizeId  )
          }
          else
          
        
          {
            0;
          }
    
    
    }
    

}


class SortParallizedMethodEnterEventGen extends Comparator[ParallizedMethodEnterEventGen]
{
    def	compare(o1 :  ParallizedMethodEnterEventGen,  o2 : ParallizedMethodEnterEventGen ) =
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
          
        
        
          if( o1.parallizeId != o2.parallizeId )
          {
             java.lang.Integer.compare( o1.parallizeId , o2.parallizeId  )
          }
          else
          
        
          {
            0;
          }
    
    
    }
    

}




