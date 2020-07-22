package com.anarsoft.race.detection.process.arrayId

import  com.anarsoft.race.detection.process.aggregate.WithLocationInClass
import com.anarsoft.race.detection.model.result.ArrayInClass;

trait ArrayIdEvent extends WithLocationInClass {
  var classOrdinal = -1;
  def classId : Int;
  def methodOrdinalAggregateId : Int;
  
  def getLocationInClass() =
  {
    
    if( methodOrdinalAggregateId == -1 )
    {
      throw new RuntimeException("fieldOrdinal not set");
    }
    
    
    new ArrayInClass(methodOrdinalAggregateId,classOrdinal);
    
    
  }
  
  
}