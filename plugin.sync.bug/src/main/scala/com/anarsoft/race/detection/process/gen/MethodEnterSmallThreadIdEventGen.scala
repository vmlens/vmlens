package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.method._

import java.nio.ByteBuffer
import java.util.Comparator;


class MethodEnterSmallThreadIdEventGen(
                                        val smallThreadId: Byte


                                        , val methodId: Int


                                        , val methodCounter: Int




)    extends ApplyMethodEventVisitor  
{
override def toString() = {
  var text =  "MethodEnterSmallThreadIdEventGen" 
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
      case that: MethodEnterSmallThreadIdEventGen => 
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


object  MethodEnterSmallThreadIdEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new MethodEnterSmallThreadIdEventGen (
     
           
            
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
     val result = new MethodEnterSmallThreadIdEventGen (
     
            data.get()
          ,  data.getInt()
          ,  data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigMethodEnterSmallThreadIdEventGen extends Comparator[MethodEnterSmallThreadIdEventGen]
{
    def	compare(o1 :  MethodEnterSmallThreadIdEventGen,  o2 : MethodEnterSmallThreadIdEventGen ) =
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


class SortMethodEnterSmallThreadIdEventGen extends Comparator[MethodEnterSmallThreadIdEventGen]
{
    def	compare(o1 :  MethodEnterSmallThreadIdEventGen,  o2 : MethodEnterSmallThreadIdEventGen ) =
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




