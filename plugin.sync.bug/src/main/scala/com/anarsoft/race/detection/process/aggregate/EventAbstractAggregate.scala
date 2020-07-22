package com.anarsoft.race.detection.process.aggregate

trait EventAbstractAggregate[ID_PER_OBJECT] {
    
    def idPerMemoryLocation       : ID_PER_OBJECT;  // MonitorId oder ObjectHashCode und field id
    // nicht ein objeckt sondern ein memory slot also field id und object hash code
   // def createID4Aggregate() : ID_4_AGGREGATE;
    
    def compareIdPerMemoryLocation( other : ID_PER_OBJECT ) : Int;
    
}