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

class StateEventFieldGen (
  val threadId  : Long
,  val classId  : Int
,  val methodId  : Int
,  val methodCounter  : Int
,  val operation  : Int
,  val objectHashCode  : Long
)    extends StateEventField 
{
override def toString() = {
  var text =  "StateEventFieldGen" 
text = text + ", threadId:" +  threadId 
text = text + ", classId:" +  classId 
text = text + ", methodId:" +  methodId 
text = text + ", methodCounter:" +  methodCounter 
text = text + ", operation:" +  operation 
text = text + ", objectHashCode:" +  objectHashCode 
text;

}



   
    
   



def accept(visitor : StateVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: StateEventFieldGen => 
        {
           if( threadId != that.threadId )
             {
               false;
             }
             else
           if( classId != that.classId )
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
           if( objectHashCode != that.objectHashCode )
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


object  StateEventFieldGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new StateEventFieldGen (
     
        
            
            data.getLong()
            , 
            
            data.getInt()
            , 
            
            data.getInt()
            , 
            
            data.getInt()
            , 
            
            data.getInt()
            , 
            
            data.getLong()
            );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new StateEventFieldGen (
     
         data.getLong()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getLong()
        );
     
     
     
     
     result;
   
   }
   


}










class SortOrigStateEventFieldGen extends Comparator[StateEventFieldGen]
{
    def	compare(o1 :  StateEventFieldGen,  o2 : StateEventFieldGen ) =
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
          
        
        if( o1.objectHashCode != o2.objectHashCode )
          {
             java.lang.Long.compare( o1.objectHashCode , o2.objectHashCode  )
          }
          else
          
        
        {
            0;
          }
    
    
    }
    

}


class SortStateEventFieldGen extends Comparator[StateEventFieldGen]
{
    def	compare(o1 :  StateEventFieldGen,  o2 : StateEventFieldGen ) =
    {
        if( o1.objectHashCode != o2.objectHashCode )
          {
             java.lang.Long.compare( o1.objectHashCode , o2.objectHashCode  )
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




