package com.anarsoft.race.detection.process.state

import java.util.ArrayList;
import com.anarsoft.race.detection.process.gen._
import com.anarsoft.race.detection.process.read._
import com.anarsoft.race.detection.process.field.FieldIdMap
import com.anarsoft.race.detection.model.graph._
import  com.anarsoft.race.detection.process.aggregate._;
import com.anarsoft.race.detection.model.result.MethodOrdinalAndPosition

trait ContextState {
  
    def  methodId2Ordinal :  ModelKey2OrdinalMap[Int];
  def fieldIdMap : FieldIdMap ;
  def  classIdMap  : FieldIdMap ;
  def methodAggregateId4Array : AggregateCollectionWithoutAggregateInfo[MethodOrdinalAndPosition,Any] ;
  var stateEventStreams  :  StreamAndStreamStatistic[StateEvent]  = null;
  var tempStateEventStreams  :  StreamAndStreamStatistic[StateEvent]  = null;
  
   var stateFieldEventList : ArrayList[StateEventField] = null;
   var stateStaticFieldEventList : ArrayList[StateEventStaticField] = null;
   var stateArrayEventList : ArrayList[StateEventArray] = null;
   
    def resetContextState()
  {
    stateFieldEventList = new ArrayList[StateEventField]();
    stateStaticFieldEventList = new ArrayList[StateEventStaticField] 
     stateArrayEventList = new ArrayList[StateEventArray] 
  
    
  }
}