package com.anarsoft.race.detection.process.state

import com.anarsoft.race.detection.process.field.FieldIdMap



trait ContextClassName {
  
  def  classIdMap  : FieldIdMap;
  var classId2Name :  Array[String] = null;
  
}