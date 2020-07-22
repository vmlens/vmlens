package com.anarsoft.race.detection.process.state

import java.util.ArrayList;
import com.anarsoft.race.detection.process.read._;
import  com.anarsoft.race.detection.process.gen._;


class TempStateReadCallback(val context : ContextState)
   extends ReadCallback[StateEvent] with StateEventVisitor {
  
  
  var currentReadSlidingWindowId = -1
 
  
    def readSlidingWindowId( slidingWindowId : Int )
   {
    
     if( context.stateFieldEventList == null)
     {
        context.stateFieldEventList = new ArrayList[StateEventField]();
     }
     
       if( context.stateStaticFieldEventList == null)
     {
        context.stateStaticFieldEventList = new ArrayList[StateEventStaticField]();
     }
     
     
     
   }
  
  
    def onEvent( event : StateEvent)
    {
      

      
  event.accept(this);
      
      
      
     
    }
    
    
       def visit( event : StateEventArray)
     {
         throw new RuntimeException("should not happen");
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