package com.anarsoft.race.detection.process.interleave.loopAlgo

import java.util.Comparator
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement


class ComparatorInterleaveEventByRunIdLoopId extends Comparator[InterleaveEventStatement] {
  
   def	compare(o1 :  InterleaveEventStatement,  o2 : InterleaveEventStatement ) =
    {
      if(  o1.loopId != o2.loopId )
      {
        Integer.compare( o1.loopId , o2.loopId );
      }
      else   if(  o1.runId != o2.runId )
      {
         Integer.compare( o1.runId , o2.runId );
      }
      else
      {
         Integer.compare( o1.runPosition , o2.runPosition );
      }
     
    }
  
}