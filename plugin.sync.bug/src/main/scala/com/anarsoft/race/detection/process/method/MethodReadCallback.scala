package com.anarsoft.race.detection.process.method

import com.anarsoft.race.detection.process.read._;
import  com.anarsoft.race.detection.process.gen._;
import  com.anarsoft.race.detection.process._;
import java.util.ArrayList;
import com.anarsoft.race.detection.process.workflow.ProcessPipeline


class MethodReadCallback(val context : ContextMethodData,val pipeline : ProcessPipeline[_]) extends ReadCallback[ApplyMethodEventVisitor]  with MethodVisitor {
  
   def readSlidingWindowId( id : Int )
   {
         if( context.currentReadMethodEvents != null )
     {
       pipeline.traceEventCount(classOf[MethodEvent], context.currentReadMethodEvents.size())
   
     }
   
        
      context.currentReadMethodEvents = new  ArrayList[MethodEvent]();
   }
   
   
    def onEvent( event : ApplyMethodEventVisitor)
   {
   

   event.accept(this);
     
     
     
   }
    
    def visit( in :  MethodEnterEventGen)
    {
      context.currentReadMethodEvents.add( in );
    }
       
def visit( in :  MethodExitEventGen)
{
     context.currentReadMethodEvents.add( in );
}


  def visit( in :  ParallizedMethodEnterEventGen)
    {
    
    
      context.currentReadMethodEvents.add( in );
    }
       
def visit( in :  ParallizedMethodExitEventGen)
{
   
     context.currentReadMethodEvents.add( in );
}

      

/*
 * class MethodEnterEventGen (
  val threadId  : Long
,  val methodId  : Int
,  val methodCounter  : Int
 */


def visit( in :  MethodEnterSmallThreadIdEventGen)
{
  
 
  
         val threadId =   context.threadNames.mappedThreadId2ThreadId.get( in.smallThreadId ).get;
       
         
         val enter = new MethodEnterEventGen(threadId,in.methodId ,in.methodCounter);
         
       //   println(enter); 
         
         context.currentReadMethodEvents.add( enter );
         
         
}
       
def visit( in :  MethodExitSmallThreadIdEventGen)
{
     val threadId =   context.threadNames.mappedThreadId2ThreadId.get( in.smallThreadId ).get;
         
            context.currentReadMethodEvents.add( new MethodExitEventGen(threadId,in.methodId ,in.methodCounter) );
}

def visit( in :  MethodEnterShortThreadIdEventGen)
{
   val threadId =   context.threadNames.shortThreadId2ThreadId.get( in.shortThreadId).get;
    val enter = new MethodEnterEventGen(threadId,in.methodId ,in.methodCounter);
     context.currentReadMethodEvents.add( enter );
}
       
def visit( in :  MethodExitShortThreadIdEventGen)
{
   val threadId =   context.threadNames.shortThreadId2ThreadId.get( in.shortThreadId ).get;
         
            context.currentReadMethodEvents.add( new MethodExitEventGen(threadId,in.methodId ,in.methodCounter) );
}












  
  
}