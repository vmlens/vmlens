package com.anarsoft.race.detection.process.field

import java.util.Comparator;


class ComparatorEventField extends Comparator[EventField] {
  
  def	compare(o1 :  EventField,  o2 : EventField ) =
    {
        if( o1.fieldId != o2.fieldId )
          {
           java.lang.Integer.compare(o1.fieldId  , o2.fieldId)
          }
          else
        {
          0;
        }
  
    }
}