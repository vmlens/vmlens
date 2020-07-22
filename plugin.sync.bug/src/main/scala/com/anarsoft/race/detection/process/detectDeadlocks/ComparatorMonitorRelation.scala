package com.anarsoft.race.detection.process.detectDeadlocks

import java.util.Comparator
import  com.anarsoft.race.detection.process.monitorRelation.MonitorRelation


class ComparatorMonitorRelation extends Comparator[MonitorRelation] {
  
   def	compare(o1 :  MonitorRelation,  o2 : MonitorRelation ) =
    {
       
          
        
        if( o1.higherMontorId != o2.higherMontorId )
          {
             java.lang.Integer.compare( o1.higherMontorId , o2.higherMontorId  )
          }
        else if( o1.lowerMonitorId != o2.lowerMonitorId )
          {
             java.lang.Integer.compare( o1.lowerMonitorId , o2.lowerMonitorId  )
          } 
         else if( o1.higherMonitorIsChild != o2.higherMonitorIsChild )
          {
             java.lang.Boolean.compare( o1.higherMonitorIsChild , o2.higherMonitorIsChild  )
          } 
         else
          {
             java.lang.Long.compare( o1.threadId , o2.threadId  )
          }
         
    }  
  
  
}