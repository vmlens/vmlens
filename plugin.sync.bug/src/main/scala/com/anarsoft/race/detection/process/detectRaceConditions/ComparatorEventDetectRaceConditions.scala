package com.anarsoft.race.detection.process.detectRaceConditions

import java.util.Comparator


class ComparatorEventDetectRaceConditions[ID_PER_OBJECT,EVENT  <:  EventDetectRaceConditions[ID_PER_OBJECT]]    extends Comparator[EVENT] {
  
  def	compare(o1 :  EVENT,  o2 : EVENT ) =
    {
        if( o1.idPerMemoryLocation != o2.idPerMemoryLocation )
        {
            o1.compareIdPerMemoryLocation (o2.idPerMemoryLocation  )
        }
          else if( o1.operation != o2.operation  )
        {
          java.lang.Integer.compare(  o2.operation , o1.operation  );
        }
          else if( o1.threadId != o2.threadId  )
        {
          java.lang.Long.compare( o1.threadId  , o2.threadId );
        }
          else
          {
            java.lang.Integer.compare(  o1.programCounter , o2.programCounter);
          }
        
        
    }  
  
  
}