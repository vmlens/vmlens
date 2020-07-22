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

class StateEventFieldInitialGen (
  val threadIdAtEvent  : Long
,  val classId  : Int
,  val methodId  : Int
,  val methodCounter  : Int
,  val operation  : Int
,  val objectHashCode  : Long
,  val slidingWindowIdAtEvent  : Int
)    extends StateEventInitialField 
{
override def toString() = {
  var text =  "StateEventFieldInitialGen" 
text = text + ", threadIdAtEvent:" +  threadIdAtEvent 
text = text + ", classId:" +  classId 
text = text + ", methodId:" +  methodId 
text = text + ", methodCounter:" +  methodCounter 
text = text + ", operation:" +  operation 
text = text + ", objectHashCode:" +  objectHashCode 
text = text + ", slidingWindowIdAtEvent:" +  slidingWindowIdAtEvent 
text;

}



   
    
   



def accept(visitor : StateInitialVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: StateEventFieldInitialGen => 
        {
           if( threadIdAtEvent != that.threadIdAtEvent )
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


object  StateEventFieldInitialGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new StateEventFieldInitialGen (
     
        
            
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
            , 
            
            data.getInt()
            );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new StateEventFieldInitialGen (
     
         data.getLong()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getLong()
        ,  data.getInt()
        );
     
     
     
     
     result;
   
   }
   


}










class SortOrigStateEventFieldInitialGen extends Comparator[StateEventFieldInitialGen]
{
    def	compare(o1 :  StateEventFieldInitialGen,  o2 : StateEventFieldInitialGen ) =
    {
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


class SortStateEventFieldInitialGen extends Comparator[StateEventFieldInitialGen]
{
    def	compare(o1 :  StateEventFieldInitialGen,  o2 : StateEventFieldInitialGen ) =
    {
        if( o1.objectHashCode != o2.objectHashCode )
          {
             java.lang.Long.compare( o1.objectHashCode , o2.objectHashCode  )
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




