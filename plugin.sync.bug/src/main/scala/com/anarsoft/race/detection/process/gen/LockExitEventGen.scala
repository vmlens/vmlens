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


class LockExitEventGen (
  val threadId  : Long
,  val programCounter  : Int
,  val order  : Int
,  val monitorId  : Int
,  val methodCounter  : Int
,  val isShared  : Boolean
,  val lockTyp  : Int
)    extends SyncActionLockExit 
{
override def toString() = {
  var text =  "LockExitEventGen" 
text = text + ", threadId:" +  threadId 
text = text + ", programCounter:" +  programCounter 
text = text + ", order:" +  order 
text = text + ", monitorId:" +  monitorId 
text = text + ", methodCounter:" +  methodCounter 
text = text + ", isShared:" +  isShared 
text = text + ", lockTyp:" +  lockTyp 
text;

}



   
    
   



def accept(visitor : SyncActionsVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: LockExitEventGen => 
        {
           if( threadId != that.threadId )
             {
               false;
             }
             else
           if( programCounter != that.programCounter )
             {
               false;
             }
             else
           if( order != that.order )
             {
               false;
             }
             else
           if( monitorId != that.monitorId )
             {
               false;
             }
             else
           if( methodCounter != that.methodCounter )
             {
               false;
             }
             else
           if( isShared != that.isShared )
             {
               false;
             }
             else
           if( lockTyp != that.lockTyp )
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


object  LockExitEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new LockExitEventGen (
     
        
            
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
            
            if( data.get( ) == 1.asInstanceOf[Byte] ) { true } else { false } 
            , 
            
            data.getInt()
            );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new LockExitEventGen (
     
         data.getLong()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        ,  data.getInt()
        ,  if( data.get( ) == 1.asInstanceOf[Byte] ) { true } else { false } 
        ,  data.getInt()
        );
     
     
     
     
     result;
   
   }
   


}










class SortOrigLockExitEventGen extends Comparator[LockExitEventGen]
{
    def	compare(o1 :  LockExitEventGen,  o2 : LockExitEventGen ) =
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
          
        
        if( o1.programCounter != o2.programCounter )
          {
             java.lang.Integer.compare( o1.programCounter , o2.programCounter  )
          }
          else
          
        
        if( o1.order != o2.order )
          {
             java.lang.Integer.compare( o1.order , o2.order  )
          }
          else
          
        
        if( o1.monitorId != o2.monitorId )
          {
             java.lang.Integer.compare( o1.monitorId , o2.monitorId  )
          }
          else
          
        
        if( o1.isShared != o2.isShared )
          {
             java.lang.Boolean.compare( o1.isShared , o2.isShared  )
          }
          else
          
        
        {
            0;
          }
    
    
    }
    

}


class SortLockExitEventGen extends Comparator[LockExitEventGen]
{
    def	compare(o1 :  LockExitEventGen,  o2 : LockExitEventGen ) =
    {
        if( o1.order != o2.order )
          {
             java.lang.Integer.compare( o1.order , o2.order  )
          }
          else
          
        
        if( o1.threadId != o2.threadId )
          {
             java.lang.Long.compare( o1.threadId , o2.threadId  )
          }
          else
          
        
        if( o1.programCounter != o2.programCounter )
          {
             java.lang.Integer.compare( o1.programCounter , o2.programCounter  )
          }
          else
          
        
        if( o1.monitorId != o2.monitorId )
          {
             java.lang.Integer.compare( o1.monitorId , o2.monitorId  )
          }
          else
          
        
        if( o1.isShared != o2.isShared )
          {
             java.lang.Boolean.compare( o1.isShared , o2.isShared  )
          }
          else
          
        
        if( o1.methodCounter != o2.methodCounter )
          {
             java.lang.Integer.compare( o1.methodCounter , o2.methodCounter  )
          }
          else
          
        
        {
            0;
          }
    
    
    }
    

}




