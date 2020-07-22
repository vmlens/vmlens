package com.anarsoft.race.detection.process.state

import com.anarsoft.race.detection.process.read._;
import  com.anarsoft.race.detection.process.gen._;
import java.util.ArrayList
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;
import com.anarsoft.race.detection.process.SortArrayList


class InitialStateReadCallback(val context : ContextInitialState)
   extends ReadCallback[StateEventInitial]  {
  
  
  var currentReadSlidingWindowId = -1
 
  
    def readSlidingWindowId( slidingWindowId : Int )
   {
     currentReadSlidingWindowId = slidingWindowId;
    
   }
  
  
    def onEvent( event : StateEventInitial)
    {
      
    context.initialStateList.add(event);
    
    if( context.initialStateList.size() > 10000 )
    {
      InitialStateReadCallback.serialize(context.initialStateList , context.stream4State);
      
      context.initialStateList = new ArrayList[StateEventInitial] ();
    }
  
      
   
     
    }
    
   
  
}


object InitialStateReadCallback
{
  
  def serialize(list : ArrayList[StateEventInitial] , stream4State : StreamWrapperWithSlidingWindow)
  {
    
    
    SortArrayList.sort(list, new ComparatorByEventSlidingWindowId());
    
    
    val iter = list.iterator();
    
    while( iter.hasNext() )
    {
      val c = iter.next();
      
      c.createJavaEvent().serialize2StreamWrapper(stream4State);
      
      
    }
    
    
    
  }
  
  
  
  
  
}


