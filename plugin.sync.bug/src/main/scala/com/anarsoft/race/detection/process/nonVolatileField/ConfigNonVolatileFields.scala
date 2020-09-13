package com.anarsoft.race.detection.process.nonVolatileField

import com.anarsoft.race.detection.process._;
import com.anarsoft.race.detection.process.setStacktraceOrdinal.PerEventListSetStacktraceOrdinal
import com.anarsoft.race.detection.process.aggregate._;
import com.anarsoft.race.detection.process.field.PerEventListBuildFieldIdMap;
import com.anarsoft.race.detection.process.detectRaceConditions._
import com.anarsoft.race.detection.process.perEventList.PerEventListStepCollection
import com.anarsoft.race.detection.process.setMonitorInfo.PerEventListSetMonitorInfo
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.model.result._;
import  com.anarsoft.race.detection.process.arrayId.PerEventCallbackSetArrayOrdinal


object ConfigNonVolatileFields {
  
  
  def getArrayAccessEventList(context : ContextNonVolatileFields) = context.arrayAccessEventList;
  def getNonVolatileFieldAccessEventList(context : ContextNonVolatileFields) = context.nonVolatileFieldAccessEventList;
  def getNonVolatileFieldAccessEventStatic(context : ContextNonVolatileFields) = context.nonVolatileFieldAccessEventStatic;
  
  
  
  
 
 
 
  def initializePerEventListSteps(perEventListSteps : PerEventListStepCollection)
  {
    
    perEventListSteps.setStacktraceOrdinal.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal(getArrayAccessEventList) )
    perEventListSteps.setStacktraceOrdinal.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal(getNonVolatileFieldAccessEventList) )
    perEventListSteps.setStacktraceOrdinal.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal(getNonVolatileFieldAccessEventStatic) )
    
    perEventListSteps.setArrayOrdinalInterleave.processPerEventListCollection.append( new PerEventCallbackSetArrayOrdinal[ArrayAccessEvent]() );
    
    
//    perEventListSteps.setStacktraceOrdinal4OwnerOfState.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal4OwnerOfState(getArrayAccessEventList) )
//    perEventListSteps.setStacktraceOrdinal4OwnerOfState.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal4OwnerOfState(getNonVolatileFieldAccessEventList) )
//    perEventListSteps.setStacktraceOrdinal4OwnerOfState.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal4OwnerOfState(getNonVolatileFieldAccessEventStatic) )
    
    
    
       
      
    
    
    
    
    // [ID_PER_OBJECT,EVENT <: EventStackTraceOrdinalAggregate[ID_PER_OBJECT]]
    
//    perEventListSteps.buildStackTraceOrdinalAggregate.processPerEventListCollection.append( PerEventCallbackBuildStackTraceOrdinalAggregate[Long,ArrayAccessEvent]( -1L , getArrayAccessEventList , AggregateCollectionWithAggregateInfo.getStackTraceOrdinalId2LocationInClass  ));
//    perEventListSteps.buildStackTraceOrdinalAggregate.processPerEventListCollection.append( PerEventCallbackBuildStackTraceOrdinalAggregate[NonVolatileFieldId,NonVolatileFieldAccessEvent]( NonVolatileFieldId(-1L,-1) , getNonVolatileFieldAccessEventList ,   AggregateCollectionWithAggregateInfo.getStackTraceOrdinalId2LocationInClass ));
//    perEventListSteps.buildStackTraceOrdinalAggregate.processPerEventListCollection.append( PerEventCallbackBuildStackTraceOrdinalAggregate[Int,NonVolatileFieldAccessEventStatic]( -1 ,  getNonVolatileFieldAccessEventStatic ,  AggregateCollectionWithAggregateInfo.getStackTraceOrdinalId2LocationInClass   ));
//    

    
    
    perEventListSteps.setFieldOrdinal.processPerEventListCollection.append(new PerEventListBuildFieldIdMap(getNonVolatileFieldAccessEventList));
    perEventListSteps.setFieldOrdinal.processPerEventListCollection.append(new PerEventListBuildFieldIdMap(getNonVolatileFieldAccessEventStatic));
    
    
    perEventListSteps.buildMethodOrdinalAggregate.processPerEventListCollection.append(
        
        PerEventCallbackBuildMethodOrdinalAggregate(1L ,  getArrayAccessEventList , AggregateCollectionWithoutAggregateInfo.getMethodAggregateId4Array)
        
       );
    
//       perEventListSteps.newBuildMethodOrdinalAggregate.processPerEventListCollection.append(
//        
//       new PerEventListBuildMethodOrdinalAggregate[Long,ArrayAccessEvent]( getArrayAccessEventList )
//        
//       );
    
    
    
//    perEventListSteps.setSortable.processPerEventListCollection.append( PerEventCallbackSetSortable( -1L  , getArrayAccessEventList ))
//    perEventListSteps.setSortable.processPerEventListCollection.append( PerEventCallbackSetSortable( NonVolatileFieldId(-1L,-1)  , getNonVolatileFieldAccessEventList ))
//    perEventListSteps.setSortable.processPerEventListCollection.append( PerEventCallbackSetSortable( -1  , getNonVolatileFieldAccessEventStatic ))
    
    
//    perEventListSteps.transferSortable.processPerEventListCollection.append( PerEventCallbackTransferSortableFlag( -1L  , getArrayAccessEventList ))
//    perEventListSteps.transferSortable.processPerEventListCollection.append( PerEventCallbackTransferSortableFlag( NonVolatileFieldId(-1L,-1)  , getNonVolatileFieldAccessEventList ))
//    perEventListSteps.transferSortable.processPerEventListCollection.append( PerEventCallbackTransferSortableFlag( -1  , getNonVolatileFieldAccessEventStatic ))
   
    
    
    
    
    
    perEventListSteps.detectRaceConditions.processPerEventListCollection.append( PerEventCallbackDetectRaceConditions( -1L  , getArrayAccessEventList ))
    perEventListSteps.detectRaceConditions.processPerEventListCollection.append( PerEventCallbackDetectRaceConditions( NonVolatileFieldId(-1L,-1)  , getNonVolatileFieldAccessEventList ))
    perEventListSteps.detectRaceConditions.processPerEventListCollection.append( PerEventCallbackDetectRaceConditions( -1  , getNonVolatileFieldAccessEventStatic ))
    
    
    perEventListSteps.setMonitorInfo4NonVolatile.processPerEventListCollection.append( PerEventListSetMonitorInfo( getArrayAccessEventList ))
    perEventListSteps.setMonitorInfo4NonVolatile.processPerEventListCollection.append( PerEventListSetMonitorInfo( getNonVolatileFieldAccessEventList ))
    perEventListSteps.setMonitorInfo4NonVolatile.processPerEventListCollection.append( PerEventListSetMonitorInfo( getNonVolatileFieldAccessEventStatic ))
 
    
 
    
  }
  
  
}