package com.anarsoft.race.detection.process.monitorRelation

import scala.collection.mutable.HashMap
import com.anarsoft.race.detection.model.WithStatementPosition

class MonitorMap {
  
  val threadId2MonitorMapPerThreadTuple = new HashMap[Long,MonitorMapPerThreadTuple]
  
  
  def newRound()
  {
    for( elem <- threadId2MonitorMapPerThreadTuple )
    {
      elem._2.newRound();
    }
  }
  
  
  def putMonitorMapPerThread(threadId : Long, monitorMapPerThread : MonitorMapPerThread)
  {
    val tuple = threadId2MonitorMapPerThreadTuple.getOrElseUpdate(threadId, new MonitorMapPerThreadTuple())
    
    tuple.put( monitorMapPerThread );
    
  }
  
  
  
  def getMonitorMapPerThreadTuple(threadId : Long) =
  {
    threadId2MonitorMapPerThreadTuple.get( threadId  )
  }
  
  
  
  
}