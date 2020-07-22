package com.anarsoft.race.detection.process.aggregate

import com.anarsoft.race.detection.process.perEventList.PerEventList4Aggregate
import java.util.ArrayList
import com.anarsoft.race.detection.process.perEventList.PerEventCallback4Aggregate
import com.anarsoft.race.detection.model.result.MemoryAccessAggregate
import com.anarsoft.race.detection.process.state.ContextBuildStackTraceOrdinal4State

class PerEventCallbackBuildStackTraceOrdinalAggregate[ID_PER_OBJECT,EVENT  <: EventStackTraceOrdinalAggregate[ID_PER_OBJECT],AGGREGATE]
 (val  aggregateColltection :    AggregateCollectionWithAggregateInfo[ID4AggregateStackTraceOrdinal,EVENT,AGGREGATE]  ,val context : ContextBuildStackTraceOrdinal4State)  
  extends PerEventCallback4Aggregate[ID_PER_OBJECT, EVENT]  {
  
  
   var currentAggregateId = -1;
   var cache :  ID4AggregateStackTraceOrdinal = null;
  
   def getId( current : EVENT ) =  current.idPerMemoryLocation;
  
  def onEventWithNewId(  current : EVENT   )
  {
     val id = getID4AggregateFromEvent(current,context);
    
     cache= id; 
     
    
     currentAggregateId =  aggregateColltection.createAggregateId(id,current)
      setAggregateIdInEvent( currentAggregateId ,   current );
       aggregateColltection.setValuesInAgggregate( current ,   currentAggregateId);
  }
  
  
  def onEventWithExistingId( current : EVENT )
  {
       if( cache.idPerMethod == current.idPerMethod && cache.stackTraceOrdinal == current.stackTraceOrdinal )
       {
         
       }
       else
       {
         val id = getID4AggregateFromEvent(current,context);
    
     cache= id; 
            aggregateColltection.addExistent(id, currentAggregateId);
       }
    
    
    
      
         setAggregateIdInEvent( currentAggregateId ,   current );
            aggregateColltection.setValuesInAgggregate( current ,   currentAggregateId);
         
  }
  

  def afterLoop()
  {
    
  }
  
  
  
   def setAggregateIdInEvent( id : Int, event : EVENT  )
    {
      event.stackTraceOrdinalAggregateId = id;
    }
   
     def getID4AggregateFromEvent( event : EVENT , context : ContextBuildAggregate ) =
     {
       event.createID4AggregateStackTraceOrdinal(); 
     }
  
  
  
}


object PerEventCallbackBuildStackTraceOrdinalAggregate
{
  
  def apply[ID_PER_OBJECT,EVENT <: EventStackTraceOrdinalAggregate[ID_PER_OBJECT]]( defaultId : ID_PER_OBJECT ,  
       getCurrentReadFieldsF : ( ContextBuildStackTraceOrdinal4State ) =>   ArrayList[EVENT]
  ,    getAggregateCollection   : ContextBuildStackTraceOrdinal4State  =>   AggregateCollectionWithAggregateInfo[ID4AggregateStackTraceOrdinal,EVENT,MemoryAccessAggregate] ) =
  {
    new PerEventList4Aggregate[ID_PER_OBJECT,EVENT,ContextBuildStackTraceOrdinal4State](
        getCurrentReadFieldsF , new Comparator4StackTraceAggregate[ID_PER_OBJECT,EVENT] , 
        defaultId : ID_PER_OBJECT ,   
        ( context : ContextBuildStackTraceOrdinal4State ) => 
          new PerEventCallbackBuildStackTraceOrdinalAggregate[ID_PER_OBJECT,EVENT,MemoryAccessAggregate](  getAggregateCollection(context) ,  context)  );
    
  }
  
}