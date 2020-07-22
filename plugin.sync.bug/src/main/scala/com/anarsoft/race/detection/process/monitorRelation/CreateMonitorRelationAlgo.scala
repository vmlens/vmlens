package com.anarsoft.race.detection.process.monitorRelation

import com.anarsoft.race.detection.process._
import java.util.ArrayList;
import scala.collection.mutable.Stack;
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet


class CreateMonitorRelationAlgo(val currentStack : Stack[BlockIdAndEvent4MonitorRelationEnter],val alreadySeenMonitorId2Count : HashMap[Int,Int],val threadId : Long , val context : ContextCreateMonitorRelation) {
  
   val monitorMapBuilder = new  MonitorMapBuilder()
   val monitorRelationSet = new HashSet[MonitorRelation]
   
   
    def processMonitorStack4Relation(
    current: Event4MonitorRelationEnter) {  
    for (p <- currentStack) {

     
        addToMap( p.event4MonitorRelationEnter , current );
        
  
    }

    }
   
   def addToMap( parent :  Event4MonitorRelationEnter , child : Event4MonitorRelationEnter )
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
     

     monitorRelationSet.add(  new MonitorRelation(higher.monitorId, lower.monitorId ,higherMonitorIsChild , parent.threadId ) )
     

   }
   
   
   
   
  
   def processMonitorEnter(enter : Event4MonitorRelationEnter)
  {
     if( !  alreadySeenMonitorId2Count.contains(enter.monitorId) )
       {
             processMonitorStack4Relation( enter);
             currentStack.push(new BlockIdAndEvent4MonitorRelationEnter( context.blockCount, enter));
             
              monitorMapBuilder.addStack(enter.programCounter, currentStack);
             
             context.blockCount = context.blockCount + 1;
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
            
            
          
       
          
          
          if( !  alreadySeenMonitorId2Count.contains(exit.monitorId) )
       {
               monitorMapBuilder.addStack(exit.programCounter, currentStack);
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
       context.threadId2MonitorStack.put( threadId , currentStack)
       
       for(rel <- monitorRelationSet )
       {
         context.monitorRelationList.add( rel );
       }
       
       context.monitorMap.putMonitorMapPerThread(threadId, monitorMapBuilder.create());
       
  }
  
  
  
}

object CreateMonitorRelationAlgo
{
  def apply(current : Event4MonitorRelation , context : ContextCreateMonitorRelation)=
  {
    val currentStack = context.threadId2MonitorStack.getOrElseUpdate(  current.threadId , new Stack[BlockIdAndEvent4MonitorRelationEnter]);
    val alreadySeenMonitorId2Count = new HashMap[Int,Int];
         
          for( elem <- currentStack )
          {
            alreadySeenMonitorId2Count.put( elem.event4MonitorRelationEnter.monitorId , 1 );
   
          
          }
          
      new  CreateMonitorRelationAlgo( currentStack , alreadySeenMonitorId2Count, current.threadId ,  context);     
          
          
  }
  
  
}







