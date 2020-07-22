package com.anarsoft.race.detection.process.state

import java.util.Comparator

class ComparatorByEventSlidingWindowId extends Comparator[StateEventInitial] {
  
  def	compare(o1 :  StateEventInitial,  o2 : StateEventInitial ) =
    {
      
          java.lang.Integer.compare( o1.slidingWindowIdAtEvent  , o2.slidingWindowIdAtEvent );
        
         
        
        
    }  
  
  
}