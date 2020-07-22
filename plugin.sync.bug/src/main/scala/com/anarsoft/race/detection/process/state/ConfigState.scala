package com.anarsoft.race.detection.process.state

import  com.anarsoft.race.detection.process.perEventList.PerEventListStepCollection
import com.anarsoft.race.detection.process.setStacktraceOrdinal.PerEventListSetStacktraceOrdinal4OwnerOfState
import com.anarsoft.race.detection.process.aggregate._
import com.anarsoft.race.detection.process.arrayId.PerEventCallbackSetArrayOrdinal

object ConfigState {
  def initializePerEventListSteps(perEventListSteps : PerEventListStepCollection)
  {
     perEventListSteps.setStacktraceOrdinal4OwnerOfState.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal4OwnerOfState( ( c ) => c.stateFieldEventList ) )
    perEventListSteps.setStacktraceOrdinal4OwnerOfState.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal4OwnerOfState( ( c ) => c.stateStaticFieldEventList ) )
       perEventListSteps.setStacktraceOrdinal4OwnerOfState.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal4OwnerOfState( ( c ) => c.stateArrayEventList ) )
     
        perEventListSteps.setArrayOrdinalState.processPerEventListCollection.append( new PerEventCallbackSetArrayOrdinal[StateEventArray]() );
     
         perEventListSteps.buildStackTraceOrdinalAggregate.processPerEventListCollection.append( PerEventCallbackBuildStackTraceOrdinalAggregate[Long,StateEventField]( -1L ,
             ( c ) => c.stateFieldEventList ,(contextBuildAggregate) => { contextBuildAggregate.stackTraceOrdinalId2LocationInClass }  ));

     
      perEventListSteps.buildStackTraceOrdinalAggregate.processPerEventListCollection.append( PerEventCallbackBuildStackTraceOrdinalAggregate[Int,StateEventStaticField]( -1 ,
             ( c ) => c.stateStaticFieldEventList ,(contextBuildAggregate) => { contextBuildAggregate.stackTraceOrdinalId2LocationInClass }  ));
      
           perEventListSteps.buildStackTraceOrdinalAggregate.processPerEventListCollection.append( PerEventCallbackBuildStackTraceOrdinalAggregate[Long,StateEventArray]( -1L ,
             ( c ) => c.stateArrayEventList ,(contextBuildAggregate) => { contextBuildAggregate.stackTraceOrdinalId2LocationInClass }  ));
      
      
     
     //    perEventListSteps.buildStackTraceOrdinalAggregate.processPerEventListCollection.append( PerEventCallbackBuildStackTraceOrdinalAggregate[NonVolatileFieldId,NonVolatileFieldAccessEvent]( NonVolatileFieldId(-1L,-1) , getNonVolatileFieldAccessEventList ,   AggregateCollectionWithAggregateInfo.getStackTraceOrdinalId2LocationInClass ));
//    perEventListSteps.buildStackTraceOrdinalAggregate.processPerEventListCollection.append( PerEventCallbackBuildStackTraceOrdinalAggregate[Int,NonVolatileFieldAccessEventStatic]( -1 ,  getNonVolatileFieldAccessEventStatic ,  AggregateCollectionWithAggregateInfo.getStackTraceOrdinalId2LocationInClass   ));
//    
     
  }
}