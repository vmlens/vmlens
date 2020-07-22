package com.anarsoft.race.detection.process.volatileField

import com.anarsoft.race.detection.process._;
import com.anarsoft.race.detection.process.setStacktraceOrdinal.PerEventListSetStacktraceOrdinal
import com.anarsoft.race.detection.process.aggregate._;
import com.anarsoft.race.detection.process.field.PerEventListBuildFieldIdMap;
import com.anarsoft.race.detection.process.syncAction.PerEventCallbackSyncPoint
import com.anarsoft.race.detection.process.perEventList.PerEventListStepCollection
import com.anarsoft.race.detection.process.setMonitorInfo.PerEventListSetMonitorInfo

object ConfigVolatileFields {
  
  
  def getVolatileFields(context : ContextVolatileField) = context.volatileAccessEventList;
  def getStaticVolatileFields(context : ContextVolatileField) = context.volatileAccessEventStatic;
  def getArrayVolatileFields(context : ContextVolatileField) =  context.volatileAccessArrayEventList;

  
  
  
  def initializePerEventListSteps(perEventListSteps : PerEventListStepCollection)
  {
    perEventListSteps.setStacktraceOrdinal.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal(getVolatileFields) )
    perEventListSteps.setStacktraceOrdinal.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal(getStaticVolatileFields) )
    
      perEventListSteps.setStacktraceOrdinal.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal(getArrayVolatileFields) )
    
//    
//   perEventListSteps.buildStackTraceOrdinalAggregate.processPerEventListCollection.append( 
//    PerEventCallbackBuildStackTraceOrdinalAggregate(-1L ,  getVolatileFields ,   AggregateCollectionWithAggregateInfo.getStackTraceOrdinalId2LocationInClass   ))
//    
//    
//   perEventListSteps.buildStackTraceOrdinalAggregate.processPerEventListCollection.append( 
//    PerEventCallbackBuildStackTraceOrdinalAggregate(-1 ,getStaticVolatileFields ,   AggregateCollectionWithAggregateInfo.getStackTraceOrdinalId2LocationInClass ))
//    
    
    
    
    perEventListSteps.setFieldOrdinal.processPerEventListCollection.append(new PerEventListBuildFieldIdMap(getVolatileFields));
    perEventListSteps.setFieldOrdinal.processPerEventListCollection.append(new PerEventListBuildFieldIdMap(getStaticVolatileFields));
    
    
    perEventListSteps.prozessSyncPointLists.processPerEventListCollection.append(PerEventCallbackSyncPoint( getVolatileFields ,  -1L ))
    perEventListSteps.prozessSyncPointLists.processPerEventListCollection.append(PerEventCallbackSyncPoint( getStaticVolatileFields ,  -1 ))
    
    
    perEventListSteps.prozessSyncPointLists.processPerEventListCollection.append(PerEventCallbackSyncPoint( getArrayVolatileFields , VolatileArrayAccessId(-1L , -1) ))

    
    
 
    
  }
  
  
  
}