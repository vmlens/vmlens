package com.anarsoft.race.detection.process.aggregate

import java.util.Comparator

class ComparatorByMehodIdAndPosition[ID_PER_OBJECT,EVENT <: EventMethodOrdinalAggregate[ID_PER_OBJECT]] extends Comparator[EVENT]  {
 
  def	compare(o1 :  EVENT,  o2 : EVENT ) =
    {
       
        if( o1.methodId  != o2.methodId)
        {
          java.lang.Integer.compare( o1.methodId , o2.methodId)
        }
          else
          {
             java.lang.Integer.compare( o1.idPerMethod , o2.idPerMethod)
          }
    }  
}