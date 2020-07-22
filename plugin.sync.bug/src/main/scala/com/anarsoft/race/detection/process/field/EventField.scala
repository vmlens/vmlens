package com.anarsoft.race.detection.process.field

import com.anarsoft.race.detection.process.aggregate.WithLocationInClass;
import com.anarsoft.race.detection.model.result.FieldInClass


trait EventField    {
  
  
  def fieldId    : Int;
  var fieldOrdinal = -1;
  def isVolatile : Boolean;
  def isStatic : Boolean;
  
  def getLocationInClass() =
  {
    
    if( fieldOrdinal == -1 )
    {
      throw new RuntimeException("fieldOrdinal not set");
    }
    
    
    new FieldInClass(fieldOrdinal,isVolatile,isStatic);
    
    
  }
  
  
  
//  def isVolatile : Boolean;
//  def isStatic   : Boolean;
  
  
}