package com.anarsoft.race.detection.process.state

import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;
import com.anarsoft.race.detection.process.read._
import java.util.ArrayList;

trait ContextInitialState {
  
  
    var initialStateEventStreams  :  StreamAndStreamStatistic[StateEventInitial]  = null;
    var initialStateList : ArrayList[StateEventInitial] = null;
    
    var  stream4State : StreamWrapperWithSlidingWindow = null;
    
     
    def initializeContextInitialState(eventDir : String)
  {
    initialStateList = new ArrayList[StateEventInitial]();
   stream4State = new StreamWrapperWithSlidingWindow( eventDir, "tempState", 0);
  
    
  }
    
    
}