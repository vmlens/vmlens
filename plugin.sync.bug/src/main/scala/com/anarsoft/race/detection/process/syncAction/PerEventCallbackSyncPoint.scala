package com.anarsoft.race.detection.process.syncAction

import com.anarsoft.race.detection.process.perEventList.PerEventCallback4Collecting
import com.anarsoft.race.detection.process.perEventList.PerEventList4Collecting
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.process.partialOrder.PartialOrderBuilder
import java.util.ArrayList
import java.util.Comparator
import com.anarsoft.race.detection.process.partialOrder.SyncPointGeneric;
import com.anarsoft.race.detection.model.WithStatementPosition;
import com.anarsoft.race.detection.process.partialOrder.ContextCreatePartialOrder
import com.anarsoft.race.detection.process.perEventList.StepProcessSingleEventList
import com.typesafe.scalalogging.Logger

class PerEventCallbackSyncPoint[UNIQUE_ID](
    val prevoiusStartHappensBeforeMap : PrevoiusStartHappensBeforeMap[SyncPointGeneric[_]],
    val threadId2LastProgramCounter : HashMap[Long,Int], 
    val context : ContextCreatePartialOrder,
    val getPartialOrderBuilder : ( ContextCreatePartialOrder ) => PartialOrderBuilder) extends  PerEventCallback4Collecting[UNIQUE_ID,SyncPointGeneric[UNIQUE_ID]] {
 
   val logger = Logger(classOf[PerEventCallbackSyncPoint[_]])
  
  def getId( event : SyncPointGeneric[UNIQUE_ID] ) = event.idPerMemoryLocation;
    
  def processPrevoiusEvents(id : UNIQUE_ID){
    
//       setStatement(None);
//       clearStatements()
       
       prevoiusStartHappensBeforeMap.foreach(   sync => { sync.asInstanceOf[SyncPointGeneric[UNIQUE_ID]].addToPrevoiusList(previousList)  }  )
       prevoiusStartHappensBeforeMap.clear();
  }
  

  def onEvent( current : SyncPointGeneric[UNIQUE_ID] , position : Int)
  {
//      setStatement(Some(current));  

      if( current.eventEndsHappensBeforeRelation() )
      {     
          prevoiusStartHappensBeforeMap.foreach(   start => 
            {        
               if( start.threadId != current.threadId )
          {
            partialOrderBuilder.leftComesBeforeRight( start ,  current );
            

            
          }
            })        
        
      }
        
      if( current.eventStartsHappensBeforeRelation()  )
      {
        prevoiusStartHappensBeforeMap.put( current );
      }
      
      threadId2LastProgramCounter.get(  current.threadId ) match
      {
        
        case None =>
          {
            threadId2LastProgramCounter.put( current.threadId , current.programCounter );
          }
        
          
        case Some(x) =>
          {
             threadId2LastProgramCounter.put( current.threadId ,  Math.max(  current.programCounter  , x));
          }
            
      }
      
  }
  
  def addPrevoiusEvents(list : ArrayList[SyncPointGeneric[UNIQUE_ID]]) =
  {

    
    val iter = list.iterator();
    val takeSet = new HashSet[UNIQUE_ID]
    
    
    while( iter.hasNext() )
    {
      val current = iter.next();
      takeSet.add( current.idPerMemoryLocation );
      
    }
    
    val newPrevoiusList = new ArrayList[SyncPointGeneric[UNIQUE_ID]]();
    
    val previous = previousList.iterator();
    
    while(  previous.hasNext() )
    {
         val current = previous.next();
         
         if( takeSet.contains(current.idPerMemoryLocation ) )
         {
           list.add( current );
         }
         else
         {
           newPrevoiusList.add( current );
         }
         
    }
    
    previousList = newPrevoiusList;
    
    logger.trace("addPrevoiusEvents list.size() "  + list.size());
    
    list;
    
    
    
  }
  
  
  def resetCallback()
  {
    partialOrderBuilder = getPartialOrderBuilder(context)
    prevoiusStartHappensBeforeMap.clear();
  
  }
  
  
  var previousList        = new ArrayList[SyncPointGeneric[UNIQUE_ID]]();
  var partialOrderBuilder : PartialOrderBuilder = null;
  
}

object PerEventCallbackSyncPoint
{
  
  
 
  
  
  
  def apply[UNIQUE_ID,STATEMENT_REFERENCE](getList : ContextProcessSyncAction => ArrayList[_ <: SyncPointGeneric[UNIQUE_ID]] , defaultUniqueId : UNIQUE_ID ) =
  {
    new PerEventList4Collecting[UNIQUE_ID,SyncPointGeneric[UNIQUE_ID],ContextProcessSyncAction](  getList , new SortBasedOnOrder[UNIQUE_ID,SyncPointGeneric[UNIQUE_ID]] , ( context :  ContextProcessSyncAction ) => 
      {
        new PerEventCallbackSyncPoint(
            context.prevoiusStartHappensBeforeMap,
            context.threadId2LastProgramCounter,context , context => context.partialOrder4SlidingWindowIdBuilder);
      } , defaultUniqueId
    
    
    );
  }
  
  
  
}




