package com.anarsoft.race.detection.process.detectDeadlocks

import com.anarsoft.race.detection.process.monitorRelation.Event4MonitorRelation
import com.anarsoft.race.detection.process.monitorRelation.Event4MonitorRelationEnter
import com.anarsoft.race.detection.process.monitorRelation.Event4MonitorRelationExit
import scala.collection.mutable.Stack
import scala.collection.mutable.HashMap


class BuildPotentialDeadlockWithParentMonitorIdsAlgo(val currentStack : Stack[Event4MonitorRelationEnter],val alreadySeenMonitorId2Count : HashMap[Int,Int],val potentialDeadlockMatcher : PotentialDeadlockMatcher,
    val threadId : Long , val context : ContextDetectDeadlocks) {
  
  
  
   
   
   
  
   
   
   
  
   def processMonitorEnter(enter : Event4MonitorRelationEnter)
  {
     if( !  alreadySeenMonitorId2Count.contains(enter.monitorId) )
       {
             potentialDeadlockMatcher.matchStack( currentStack , enter );
             currentStack.push(enter);
             
       }
         
      
            val count =  alreadySeenMonitorId2Count.getOrElseUpdate(  enter.monitorId  , 0 )
            alreadySeenMonitorId2Count.put(   enter.monitorId , count + 1);
  }
  
  
  def processMonitorExit(exit : Event4MonitorRelationExit)
  {
      if (!currentStack.isEmpty) {
          val removed =  currentStack.pop();
           
          
            val count =  alreadySeenMonitorId2Count.getOrElseUpdate(  exit.monitorId  , 0 ) - 1
          
            
            if( count <= 0 )
            {
                 alreadySeenMonitorId2Count.remove(exit.monitorId)
            }
            else
            {
               alreadySeenMonitorId2Count.put(   exit.monitorId , count );
            }
                   
        }
  }
  
  
  
  def processEvent(current : Event4MonitorRelation)
  {
     current.onMonitorEnter(    processMonitorEnter  );
     current.onMonitorExit(     processMonitorExit  );
     
     
  }
  
  
  def addResult2Context()
  {
       context.threadId2MonitorStack4DetectDeadlocks.put( threadId , currentStack)
       potentialDeadlockMatcher.add2List( context.potentialDeadlockWithParentMonitorIdsList );
  }
  
  
}

object BuildPotentialDeadlockWithParentMonitorIdsAlgo
{
 
     def apply(current : Event4MonitorRelation , context : ContextDetectDeadlocks)=
  {
       
       context.potentialDeadlockMap.getMatcher(current.threadId) match
       {
         case None =>
           {
             None;
           }
           
         case Some(m) =>
           {
               val currentStack = context.threadId2MonitorStack4DetectDeadlocks.getOrElseUpdate(  current.threadId , new Stack[Event4MonitorRelationEnter]);
    val alreadySeenMonitorId2Count = new HashMap[Int,Int];
         
          for( elem <- currentStack )
          {
            alreadySeenMonitorId2Count.put( elem.monitorId, 1 );
   
          
          }
          
              Some(  new  BuildPotentialDeadlockWithParentMonitorIdsAlgo( currentStack , alreadySeenMonitorId2Count,m ,  current.threadId ,  context)); 
           }
         
         
       }
       
       
      
          
          
  
  }
  
  
  
}