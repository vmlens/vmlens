package com.anarsoft.race.detection.process.detectDeadlocks

import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Stack
import com.anarsoft.race.detection.process.monitorRelation.Event4MonitorRelationEnter
import com.anarsoft.race.detection.process.monitorRelation.Event4MonitorRelationEnter
import java.util.ArrayList
import com.anarsoft.race.detection.model.result._;

class PotentialDeadlockMatcher(val child2Parent : HashMap[Int,HashSet[Int]]) {
  
  
  val potentialDeadlock2StackTraceOrdinalTuple = new HashMap[PotentialDeadlockAsKey, DeadlockLocation]
  
  def matchStack( parentStack : Stack[Event4MonitorRelationEnter] , potentialChild : Event4MonitorRelationEnter )
  {
    child2Parent.get( potentialChild.monitorId  ) match
    {
      
      case None =>
        {
          
        }
      
      case Some(parentSet) =>
        {
        
          
          for( p  <- parentStack )
          {
            
            
            if( parentSet.contains(p.monitorId) )
            {
              
              /*
               * for stack geht von oben nach unten
               */
              
              val parentList = new ArrayBuffer[Int]
              var addToParents = false;
              
              for( x <- parentStack )
              {
                 if(addToParents)
                {
                  parentList.append( x.monitorId )
                }
                
                
                if( x.monitorId == p.monitorId )
                {
                  addToParents = true;
                }
                
               
                  
                
                
              }
              
              /*
               * PotentialDeadlockWithParentMonitorIds(val higherMontorId : Int, val lowerMonitorId : Int , 
    val higherMonitorIsChild : Boolean , val threadId : Long  , val parentMonitorIds : List[Int] )
               */
              
         
              
            addToMap(  p , potentialChild , parentList.toList);
              
              
              
            }
            
            
          }
          
          
          
        }
      
    }
  }
  
  
    def addToMap( parent :  Event4MonitorRelationEnter , child : Event4MonitorRelationEnter , parentMonitorIds : List[Int] )
   {
     var lower = parent;
     var higher = child;
     var higherMonitorIsChild  = true;
     
     if(  child.monitorId > parent.monitorId )
     {
       lower = child;
       higher = parent;
       higherMonitorIsChild = false;
       
     }
     
       val potentialDeadlockWithParentMonitorIds =  new PotentialDeadlockAsKey(higher.monitorId, lower.monitorId ,higherMonitorIsChild , parent.threadId , parentMonitorIds)
     
      potentialDeadlock2StackTraceOrdinalTuple.put( potentialDeadlockWithParentMonitorIds , new DeadlockLocation(
          StackTraceOrdinalAndMonitor( StackTraceOrdinal(parent.stackTraceOrdinal) , parent.position() , parent.monitorId ) , 
          StackTraceOrdinalAndMonitor(StackTraceOrdinal(child.stackTraceOrdinal) , child.position() , child.monitorId )))

   
       
     

   }
    
    def add2List( potentialDeadlockWithParentMonitorIdsList : ArrayList[PotentialDeadlock])
    {
      for(elem <- potentialDeadlock2StackTraceOrdinalTuple)
      {
        potentialDeadlockWithParentMonitorIdsList.add(  new  PotentialDeadlock(elem._1 , elem._2) );
      }
    }
   
  
  

  
}