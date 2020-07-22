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

class StateEventStaticFieldInitialGen (
  val threadIdAtEvent  : Long
,  val fieldId  : Int
,  val methodId  : Int
,  val methodCounter  : Int
,  val operation  : Int
,  val slidingWindowIdAtEvent  : Int
)    extends StateEventStaticInitialField 
{
override def toString() = {
  var text =  "StateEventStaticFieldInitialGen" 
text = text + ", threadIdAtEvent:" +  threadIdAtEvent 
text = text + ", fieldId:" +  fieldId 
text = text + ", methodId:" +  methodId 
text = text + ", methodCounter:" +  methodCounter 
text = text + ", operation:" +  operation 
text = text + ", slidingWindowIdAtEvent:" +  slidingWindowIdAtEvent 
text;

}



   
    
   



def accept(visitor : StateInitialVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: StateEventStaticFieldInitialGen => 
        {
           if( threadIdAtEvent != that.threadIdAtEvent )
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
           if( slidingWindowIdAtEvent != that.slidingWindowIdAtEvent )
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


object  StateEventStaticFieldInitialGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new StateEventStaticFieldInitialGen (
     
        
            
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
            
            data.getInt()
            );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new StateEventStaticFieldInitialGen (
     
         data.getLong()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        );
     
     
     
     
     result;
   
   }
   


}










class SortOrigStateEventStaticFieldInitialGen extends Comparator[StateEventStaticFieldInitialGen]
{
    def	compare(o1 :  StateEventStaticFieldInitialGen,  o2 : StateEventStaticFieldInitialGen ) =
    {
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


class SortStateEventStaticFieldInitialGen extends Comparator[StateEventStaticFieldInitialGen]
{
    def	compare(o1 :  StateEventStaticFieldInitialGen,  o2 : StateEventStaticFieldInitialGen ) =
    {
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




