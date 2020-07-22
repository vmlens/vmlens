package com.anarsoft.race.detection.process.monitorRelation


import java.util.ArrayList;



class MonitorMapPerThread(val  programPosition2Interval :  ListBasedMap[MonitorListInterval],  val blockIdAndMethodIdList : ArrayList[MonitorIdBlockIdStackTraceOrdinal]) {
  
  

  
  
  
  
  
  
  
  def getMonitorInfoSet(programCounter : Int) =
  {
    
   
    /**
     * 
     * ob kleiner oder kleiner gleich sollte keine rolle spielen
     * 
     * 
     */
    
        programPosition2Interval.get(programCounter) match
        {
          
          case None =>
            {
              
            
              
              None;
            }
          
          case Some(interval) =>
            {
                 if( interval.start == interval.end )
         {
                 
           None;
         }
         else
         {
             Some(new MonitorInfo(blockIdAndMethodIdList.subList(interval.start, interval.end)) );
           
         }
            }
          
          
        }
        
        
    
  }
 
    
    
    
    
  
 
}










