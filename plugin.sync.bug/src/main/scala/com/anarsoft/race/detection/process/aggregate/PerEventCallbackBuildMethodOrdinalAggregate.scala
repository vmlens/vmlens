package com.anarsoft.race.detection.process.aggregate

import com.anarsoft.race.detection.model.result.MethodOrdinalAndPosition
import com.anarsoft.race.detection.process.perEventList.PerEventList4Aggregate
import java.util.ArrayList
import com.anarsoft.race.detection.process.perEventList.PerEventCallback4Aggregate
import com.anarsoft.race.detection.process.nonVolatileField.ContextNonVolatileFields


class PerEventCallbackBuildMethodOrdinalAggregate[ID_PER_OBJECT, EVENT <: EventMethodOrdinalAggregate[ID_PER_OBJECT]](val aggregateColltection: AggregateCollection[MethodOrdinalAndPosition, EVENT], val context: ContextNonVolatileFields)
  extends PerEventCallback4Aggregate[ID_PER_OBJECT, EVENT] {

  var currentAggregateId = -1;
  var cache: CacheMethodOrdinalAggregate = null;
  
  
  

  def getId(current: EVENT) = current.idPerMemoryLocation;

  def onEventWithNewId(current: EVENT) {

    val c = getID4AggregateFromEvent(current, context)

    cache = new CacheMethodOrdinalAggregate(current.methodId, current.idPerMethod, c);
    currentAggregateId = aggregateColltection.createAggregateId(c, current);

   
   
    setAggregateIdInEvent(currentAggregateId, current);
  }

  def onEventWithExistingId(current: EVENT) {

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

  def setAggregateIdInEvent(id: Int, event: EVENT) {
    event.methodOrdinalAggregateId = id;
  }

  def getID4AggregateFromEvent(event: EVENT, context: ContextBuildAggregate) =
    {
      new MethodOrdinalAndPosition(context.methodId2Ordinal.getOrAddOrdinal(event.methodId), event.idPerMethod);
    }

}

object PerEventCallbackBuildMethodOrdinalAggregate {

  def apply[ID_PER_OBJECT, EVENT <: EventMethodOrdinalAggregate[ID_PER_OBJECT]](
    defaultId: ID_PER_OBJECT,
    getCurrentReadFieldsF: (ContextNonVolatileFields) => ArrayList[EVENT], getAggregateCollection: ContextNonVolatileFields => AggregateCollection[MethodOrdinalAndPosition, EVENT]) =
    {
      new PerEventList4Aggregate[ID_PER_OBJECT, EVENT, ContextNonVolatileFields](
        getCurrentReadFieldsF, new Comparator4MethodOrdinalAggregate[ID_PER_OBJECT, EVENT],
        defaultId: ID_PER_OBJECT,
        (context: ContextNonVolatileFields) => new PerEventCallbackBuildMethodOrdinalAggregate[ID_PER_OBJECT, EVENT](getAggregateCollection(context), context));

    }

}