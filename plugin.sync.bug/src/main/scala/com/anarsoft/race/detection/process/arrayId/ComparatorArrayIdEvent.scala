package com.anarsoft.race.detection.process.arrayId

import java.util.Comparator

class ComparatorArrayIdEvent[EVENT <: ArrayIdEvent]  extends Comparator[EVENT] {
  
  def	compare(o1 :  EVENT,  o2 : EVENT ) =
    {
      
         java.lang.Integer.compare(  o1.classId , o2.classId);
          
        
        
    }  
  
  

}