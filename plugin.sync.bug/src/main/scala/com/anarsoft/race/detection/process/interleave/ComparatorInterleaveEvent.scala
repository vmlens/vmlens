package com.anarsoft.race.detection.process.interleave

import java.util.Comparator

class ComparatorInterleaveEvent extends Comparator[InterleaveEventStatement] {
  
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