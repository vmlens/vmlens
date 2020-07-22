package com.anarsoft.race.detection.process.state

import com.anarsoft.race.detection.process.read._;
import  com.anarsoft.race.detection.process.gen._;


class StateReadCallback(val context : ContextState)
   extends ReadCallback[StateEvent] with StateEventVisitor {
  
  
  var currentReadSlidingWindowId = -1
 
  
    def readSlidingWindowId( slidingWindowId : Int )
   {
     currentReadSlidingWindowId = slidingWindowId;
     context.resetContextState()
   }
  
  
    def onEvent( event : StateEvent)
    {
      
    
  event.accept(this);
      
      
      
     
    }
    
     def visit( event : StateEventArray)
     {
       
//       if( event.objectHashCode == 298L )
//       {
//           println(event);
//       }
         
       
     
       
         context.stateArrayEventList.add(event);
     }
    
    
    
     def visit( event : StateEventStaticField)
     {
         context.stateStaticFieldEventList.add(event);
     }
    
    
    def visit( event : StateEventField)
    {
      context.stateFieldEventList.add(event);
    }
    
  
}