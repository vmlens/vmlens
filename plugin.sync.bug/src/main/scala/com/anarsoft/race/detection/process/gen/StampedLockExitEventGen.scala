package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.syncAction._

import java.nio.ByteBuffer
import java.util.Comparator;


class StampedLockExitEventGen (
  val threadId  : Long


,  val programCounter  : Int


,  val order  : Int


,  val monitorId  : Int


,  val methodCounter  : Int


  , val isShared: Boolean


  , val lockTyp: Int


  , val stampedLockMethodId: Int


  , val loopId: Int


  , val runId: Int


  , val runPosition: Int


                              ) extends StampedLockExitInterleave {
  override def toString() = {
    var text = "StampedLockExitEventGen"
    text = text + ", threadId:" + threadId
    text = text + ", programCounter:" + programCounter
    text = text + ", order:" + order
    text = text + ", monitorId:" + monitorId
    text = text + ", methodCounter:" + methodCounter
    text = text + ", isShared:" + isShared
    text = text + ", lockTyp:" + lockTyp
    text = text + ", stampedLockMethodId:" + stampedLockMethodId
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition

    text;

  }



   
    
   



def accept(visitor : SyncActionsVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: StampedLockExitEventGen => 
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
             else if (stampedLockMethodId != that.stampedLockMethodId) {
               false;
             }
             else if (loopId != that.loopId) {
               false;
             }
             else if (runId != that.runId) {
               false;
             }
             else if (runPosition != that.runPosition) {
               false;
             }
             else
               true;
        }


      case _ => false
    }
  }



}


object  StampedLockExitEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new StampedLockExitEventGen (
     
           
            
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

       if (data.get() == 1.asInstanceOf[Byte]) {
         true
       } else {
         false
       }

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
     val result = new StampedLockExitEventGen(

       data.getLong()
       , data.getInt()
       , data.getInt()
       , data.getInt()
       , data.getInt()
       , if (data.get() == 1.asInstanceOf[Byte]) {
         true
       } else {
         false
       }
       , data.getInt()
       , data.getInt()
       , data.getInt()
       , data.getInt()
       , data.getInt()


     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigStampedLockExitEventGen extends Comparator[StampedLockExitEventGen]
{
    def	compare(o1 :  StampedLockExitEventGen,  o2 : StampedLockExitEventGen ) =
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


class SortStampedLockExitEventGen extends Comparator[StampedLockExitEventGen]
{
    def	compare(o1 :  StampedLockExitEventGen,  o2 : StampedLockExitEventGen ) =
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




