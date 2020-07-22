package com.anarsoft.race.detection.process.field

import com.anarsoft.race.detection.process.volatileField.ContextVolatileField
import com.anarsoft.race.detection.process.nonVolatileField.ContextNonVolatileFields;


trait ContextFieldIdMap extends ContextVolatileField with ContextNonVolatileFields {
  
  var fieldIdMap : FieldIdMap = null;
  var classIdMap  : FieldIdMap = null;
  
  def initializeContextFieldIdMap()
  {
    fieldIdMap = new FieldIdMap(); 
    classIdMap = new FieldIdMap(); 
  }
  
}