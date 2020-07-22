package com.anarsoft.race.detection.process.scheduler

import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.gen._;
import java.util.ArrayList



class SchedulerReadCallback(val context : ContextSchedulerRead )  extends ReadCallback[SchedulerEvent]  with SchedulerVisitor   {
  
  def onEvent(event: SchedulerEvent)
  {

    event.accept(this);
   
    

  }
  
  def visit( in :  LoopWarningEventGen)
  {
    context.interleaveEventList.add(in);
  } 
  
    
def visit( in :  LoopStartEventGen)
{
   context.interleaveEventList.add(in);
}
       
def visit( in :  LoopEndEventGen)
{
   context.interleaveEventList.add(in);
}
       
def visit( in :  RunStartEventGen)
{
   context.interleaveEventList.add(in);
}


def visit( in :  RunEndEventGen)
{
   context.interleaveEventList.add(in);
}
  
  
  
  
  
       
def visit( in :  MethodAtomicEnterEventGen)
{
  
  
  in.methodOrdinal =
   context.methodId2Ordinal.getOrAddOrdinal( in.methodId )
   context.atomicMethodEventList.add(in);
  
   context.interleaveEventList.add(in);
  
}
       



def visit( in :  MethodAtomicExitEventGen)
{
  
  
    in.methodOrdinal =
   context.methodId2Ordinal.getOrAddOrdinal( in.methodId )
   context.atomicMethodEventList.add(in);
  
     context.interleaveEventList.add(in);
    
}
  

def visit( in :  MethodCallbackEnterEventGen)
{
  context.atomicMethodEventList.add(in);
  
   context.interleaveEventList.add(in);
}  
  
def visit( in :  MethodCallbackExitEventGen)
{
   context.atomicMethodEventList.add(in);
   
    context.interleaveEventList.add(in);
}
  
  
  
  
  
   
  
  
  def readSlidingWindowId(id: Int)
  {
   context.initializeContextSetStacktraceOrdinal()
  
   // context.fieldPipelineCollection.startReadingDirectMemory();
  }
}