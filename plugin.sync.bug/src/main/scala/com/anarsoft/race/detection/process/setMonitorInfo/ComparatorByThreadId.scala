package com.anarsoft.race.detection.process.setMonitorInfo

import java.util.Comparator

class ComparatorByThreadId extends Comparator[EventSetMonitorInfo] {
  
    def	compare(o1 :  EventSetMonitorInfo,  o2 : EventSetMonitorInfo ) =
    {
        if( o1.threadId != o2.threadId )
          {
             java.lang.Long.compare( o1.threadId , o2.threadId  )
          }
          else
           0;
        
    }    
            
  
  
}