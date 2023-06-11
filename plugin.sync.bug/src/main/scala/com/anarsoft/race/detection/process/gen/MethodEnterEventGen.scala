package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.method._

import java.nio.ByteBuffer
import java.util.Comparator;


class MethodEnterEventGen(
                           val threadId: Long


                           , val methodId: Int


                           , val methodCounter: Int




)    extends MethodEnterEvent  
{
override def toString() = {
  var text =  "MethodEnterEventGen" 
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
      case that: MethodEnterEventGen => 
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


object  MethodEnterEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new MethodEnterEventGen (
     
           
            
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
     val result = new MethodEnterEventGen (
     
            data.getLong()
          ,  data.getInt()
          ,  data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigMethodEnterEventGen extends Comparator[MethodEnterEventGen]
{
    def	compare(o1 :  MethodEnterEventGen,  o2 : MethodEnterEventGen ) =
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


class SortMethodEnterEventGen extends Comparator[MethodEnterEventGen]
{
    def	compare(o1 :  MethodEnterEventGen,  o2 : MethodEnterEventGen ) =
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




