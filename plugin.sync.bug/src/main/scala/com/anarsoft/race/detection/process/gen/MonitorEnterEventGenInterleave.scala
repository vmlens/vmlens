package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.monitor._

import java.nio.ByteBuffer
import java.util.Comparator;


class MonitorEnterEventGenInterleave(
                                      val threadId: Long


                                      , val programCounter: Int


                                      , val order: Int


,  val monitorId  : Int


,  val methodCounter  : Int


,  val methodId  : Int


,  val position  : Int


,  val loopId  : Int


,  val runId  : Int


,  val runPosition  : Int




)    extends MonitorEnterEventInterleave  
{
override def toString() = {
  var text =  "MonitorEnterEventGenInterleave" 
  text = text + ", threadId:" +  threadId 
  text = text + ", programCounter:" +  programCounter 
  text = text + ", order:" +  order 
  text = text + ", monitorId:" +  monitorId 
  text = text + ", methodCounter:" +  methodCounter 
  text = text + ", methodId:" +  methodId 
  text = text + ", position:" +  position 
  text = text + ", loopId:" +  loopId 
  text = text + ", runId:" +  runId 
  text = text + ", runPosition:" +  runPosition 

text;

}



   
    
   



def accept(visitor : MonitorVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: MonitorEnterEventGenInterleave => 
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
            
             if( methodId != that.methodId )
             {
               false;
             }
             else
            
             if( position != that.position )
             {
               false;
             }
             else
            
             if( loopId != that.loopId )
             {
               false;
             }
             else
            
             if( runId != that.runId )
             {
               false;
             }
             else
            
             if( runPosition != that.runPosition )
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


object  MonitorEnterEventGenInterleave 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new MonitorEnterEventGenInterleave (
     
           
            
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
     val result = new MonitorEnterEventGenInterleave (
     
            data.getLong()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
          ,  data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigMonitorEnterEventGenInterleave extends Comparator[MonitorEnterEventGenInterleave]
{
    def	compare(o1 :  MonitorEnterEventGenInterleave,  o2 : MonitorEnterEventGenInterleave ) =
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
          
        
        
          if( o1.methodId != o2.methodId )
          {
             java.lang.Integer.compare( o1.methodId , o2.methodId  )
          }
          else
          
        
        
          if( o1.monitorId != o2.monitorId )
          {
             java.lang.Integer.compare( o1.monitorId , o2.monitorId  )
          }
          else
          
        
        
          if( o1.position != o2.position )
          {
             java.lang.Integer.compare( o1.position , o2.position  )
          }
          else
          
        
          {
            0;
          }
    
    
    }
    

}


class SortMonitorEnterEventGenInterleave extends Comparator[MonitorEnterEventGenInterleave]
{
    def	compare(o1 :  MonitorEnterEventGenInterleave,  o2 : MonitorEnterEventGenInterleave ) =
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
          
        
        
          if( o1.position != o2.position )
          {
             java.lang.Integer.compare( o1.position , o2.position  )
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




