package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.method._

import java.nio.ByteBuffer
import java.util.Comparator;


class MethodExitEventGen (
  val threadId  : Long


,  val methodId  : Int


,  val methodCounter  : Int




)    extends MethodExitEvent 
{
override def toString() = {
  var text =  "MethodExitEventGen" 
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
      case that: MethodExitEventGen => 
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


object  MethodExitEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new MethodExitEventGen (
     
           
            
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
     val result = new MethodExitEventGen (
     
            data.getLong()
          ,  data.getInt()
          ,  data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigMethodExitEventGen extends Comparator[MethodExitEventGen]
{
    def	compare(o1 :  MethodExitEventGen,  o2 : MethodExitEventGen ) =
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


class SortMethodExitEventGen extends Comparator[MethodExitEventGen]
{
    def	compare(o1 :  MethodExitEventGen,  o2 : MethodExitEventGen ) =
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




