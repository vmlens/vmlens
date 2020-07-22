package com.anarsoft.race.detection.process.aggregate



trait EventMethodOrdinalAggregate[ID_PER_OBJECT] extends EventAbstractAggregate[ID_PER_OBJECT] {
  
  
   def methodId          : Int;
   def idPerMethod       : Int; // field id über alle methoden oder position
   
   
 
 //   def createMethodOrdinalAndPosition()  = new MethodOrdinalAndPosition(  methodOrdinal : Int,  idPerMethod : Int );
    
    
    var methodOrdinalAggregateId = -1;
    
    // var newMethodOrdinalAggregateId = -1;
  
  
}