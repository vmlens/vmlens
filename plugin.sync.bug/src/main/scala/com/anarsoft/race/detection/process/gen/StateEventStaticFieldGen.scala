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

class StateEventStaticFieldGen (
  val threadId  : Long
,  val fieldId  : Int
,  val methodId  : Int
,  val methodCounter  : Int
,  val operation  : Int
)    extends StateEventStaticField 
{
override def toString() = {
  var text =  "StateEventStaticFieldGen" 
text = text + ", threadId:" +  threadId 
text = text + ", fieldId:" +  fieldId 
text = text + ", methodId:" +  methodId 
text = text + ", methodCounter:" +  methodCounter 
text = text + ", operation:" +  operation 
text;

}



   
    
   



def accept(visitor : StateVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: StateEventStaticFieldGen => 
        {
           if( threadId != that.threadId )
             {
               false;
             }
             else
           if( fieldId != that.fieldId )
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
           if( operation != that.operation )
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


object  StateEventStaticFieldGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new StateEventStaticFieldGen (
     
        
            
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
     val result = new StateEventStaticFieldGen (
     
         data.getLong()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        );
     
     
     
     
     result;
   
   }
   


}










class SortOrigStateEventStaticFieldGen extends Comparator[StateEventStaticFieldGen]
{
    def	compare(o1 :  StateEventStaticFieldGen,  o2 : StateEventStaticFieldGen ) =
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
          
        
        if( o1.fieldId != o2.fieldId )
          {
             java.lang.Integer.compare( o1.fieldId , o2.fieldId  )
          }
          else
          
        
        if( o1.operation != o2.operation )
          {
             java.lang.Integer.compare( o1.operation , o2.operation  )
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


class SortStateEventStaticFieldGen extends Comparator[StateEventStaticFieldGen]
{
    def	compare(o1 :  StateEventStaticFieldGen,  o2 : StateEventStaticFieldGen ) =
    {
        if( o1.fieldId != o2.fieldId )
          {
             java.lang.Integer.compare( o1.fieldId , o2.fieldId  )
          }
          else
          
        
        if( o1.threadId != o2.threadId )
          {
             java.lang.Long.compare( o1.threadId , o2.threadId  )
          }
          else
          
        
        if( o1.operation != o2.operation )
          {
             java.lang.Integer.compare( o1.operation , o2.operation  )
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




