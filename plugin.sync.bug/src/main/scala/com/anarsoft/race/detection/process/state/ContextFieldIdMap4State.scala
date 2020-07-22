package com.anarsoft.race.detection.process.state

import com.anarsoft.race.detection.process.field.FieldIdMap

trait ContextFieldIdMap4State {
  
   var fieldIdMap : FieldIdMap = null;
   var classIdMap  : FieldIdMap = null;
  
  def initializeContextFieldIdMap()
  {
    fieldIdMap = new FieldIdMap(); 
    classIdMap = new FieldIdMap(); 
  }
  
  
}