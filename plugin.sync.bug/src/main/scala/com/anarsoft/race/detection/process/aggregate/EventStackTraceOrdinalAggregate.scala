package com.anarsoft.race.detection.process.aggregate

trait EventStackTraceOrdinalAggregate[ID_PER_OBJECT] extends EventAbstractAggregate[ID_PER_OBJECT] {
  
  
  
  
   def idPerMemoryLocation           : ID_PER_OBJECT;  // MonitorId oder ObjectHashCode
   def stackTraceOrdinal     : Int;
   def idPerMethod           : Int; // field id über alle methoden oder position
  
   
    def createID4AggregateStackTraceOrdinal()  = new ID4AggregateStackTraceOrdinal(  stackTraceOrdinal : Int,  idPerMethod : Int );
    
    var stackTraceOrdinalAggregateId  = -1;
  
}