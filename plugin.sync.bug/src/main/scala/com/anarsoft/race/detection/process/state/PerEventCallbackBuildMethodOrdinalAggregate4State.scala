package com.anarsoft.race.detection.process.state

import com.anarsoft.race.detection.model.result.MethodOrdinalAndPosition
import com.anarsoft.race.detection.process.perEventList.PerEventList4Aggregate
import java.util.ArrayList
import com.anarsoft.race.detection.process.perEventList._
import com.anarsoft.race.detection.process.nonVolatileField.ContextNonVolatileFields
import com.anarsoft.race.detection.process.aggregate._;

class PerEventCallbackBuildMethodOrdinalAggregate4State(val aggregateColltection: AggregateCollection[MethodOrdinalAndPosition, StateEventArray], val context: ContextState)
  extends PerEventCallback4Aggregate[Long, StateEventArray] {

  var currentAggregateId = -1;
  var cache: CacheMethodOrdinalAggregate = null;
  
  
  

  def getId(current: StateEventArray) = current.idPerMemoryLocation;

  def onEventWithNewId(current: StateEventArray) {

    val c = getID4AggregateFromEvent(current, context)

    cache = new CacheMethodOrdinalAggregate(current.methodId, current.idPerMethod, c);
    currentAggregateId = aggregateColltection.createAggregateId(c, current);

   
   
    setAggregateIdInEvent(currentAggregateId, current);
  }

  def onEventWithExistingId(current: StateEventArray) {

    if (cache.methodId == current.methodId && cache.position == current.idPerMethod) {

    } else {
      val c = getID4AggregateFromEvent(current, context)
      cache = new CacheMethodOrdinalAggregate(current.methodId, current.idPerMethod, c);
      aggregateColltection.addExistent(c, currentAggregateId);
    }

    setAggregateIdInEvent(currentAggregateId, current);
  }

  def afterLoop() {

  }

  def setAggregateIdInEvent(id: Int, event: StateEventArray) {
    event.methodOrdinalAggregateId = id;
  }

  def getID4AggregateFromEvent(event: StateEventArray, context: ContextState) =
    {
      new MethodOrdinalAndPosition(context.methodId2Ordinal.getOrAddOrdinal(event.methodId), event.idPerMethod);
    }

}

object PerEventCallbackBuildMethodOrdinalAggregate4State
{
  def apply() =
  {
          val perEventList = 
          new PerEventList4Aggregate[Long, StateEventArray, ContextState](
        ( c ) => c.stateArrayEventList, new Comparator4MethodOrdinalAggregate[Long, StateEventArray],
       -1L ,
        (context: ContextState) => new PerEventCallbackBuildMethodOrdinalAggregate4State( context.methodAggregateId4Array , context));
    
    
    
    
    
    
    
    
    
   new StepProcessSingleEventList[ContextState](perEventList , classOf[ContextState]);
  }
  
  /*
   *  perEventListSteps.buildMethodOrdinalAggregate.processPerEventListCollection.append(
        
        PerEventCallbackBuildMethodOrdinalAggregate(1L ,  getArrayAccessEventList , AggregateCollectionWithoutAggregateInfo.getMethodAggregateId4Array)
        
       );
    
   */
  
  
}









//object PerEventCallbackBuildMethodOrdinalAggregate {
//
//  def apply[ID_PER_OBJECT, EVENT <: EventMethodOrdinalAggregate[ID_PER_OBJECT]](
//    defaultId: ID_PER_OBJECT,
//    getCurrentReadFieldsF: (ContextNonVolatileFields) => ArrayList[EVENT], getAggregateCollection: ContextNonVolatileFields => AggregateCollection[MethodOrdinalAndPosition, EVENT]) =
//    {
//      new PerEventList4Aggregate[ID_PER_OBJECT, EVENT, ContextNonVolatileFields](
//        getCurrentReadFieldsF, new Comparator4MethodOrdinalAggregate[ID_PER_OBJECT, EVENT],
//        defaultId: ID_PER_OBJECT,
//        (context: ContextNonVolatileFields) => new PerEventCallbackBuildMethodOrdinalAggregate[ID_PER_OBJECT, EVENT](getAggregateCollection(context), context));
//
//    }
//
//}