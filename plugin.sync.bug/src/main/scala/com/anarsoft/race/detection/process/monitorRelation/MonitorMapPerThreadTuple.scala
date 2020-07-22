package com.anarsoft.race.detection.process.monitorRelation

class MonitorMapPerThreadTuple(  ) {
  
  var lower   :  Option[MonitorMapPerThread] = None;
  var higher  :  Option[MonitorMapPerThread] = None;
  
  
  
  def newRound()
  {
    
    lower = higher;
    higher = None;
  }
  
  
  
  def put(monitorMapPerThread : MonitorMapPerThread)
  {
    
    if( ! higher.isEmpty )
    {
      throw new RuntimeException("higher already set");
    }
    
    
    higher = Some(monitorMapPerThread);
  }
  
  
  
  
  def getMonitorInfo(programCounter : Int) =
  {
    
    
    
    var result =
    higher match
    {
      
      case None =>
        {
          None;
        }
     
      case Some(x) =>
        {
           x.getMonitorInfoSet( programCounter );
        }
        
      
    }
    
    
    
    if( ! result.isEmpty )
    {
      result
    }
    else
    {
      lower match
    {
      
      case None =>
        {
          None;
        }
     
      case Some(x) =>
        {
          
           
          
           x.getMonitorInfoSet( programCounter );
        }
        
      
    }
      
      
    }
    
    
    
    
    
  }
  
  
  
  
}