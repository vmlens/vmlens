package com.anarsoft.race.detection.process.interleave.eventList

import java.util.Comparator
import com.anarsoft.race.detection.process.interleave.LoopOrRunEvent


class ComparatorLoopOrRunEvent extends Comparator[LoopOrRunEvent] {
  
   def	compare(o1 :  LoopOrRunEvent,  o2 : LoopOrRunEvent ) =
    {
       o1.compare(o2);
     
    }
  
}