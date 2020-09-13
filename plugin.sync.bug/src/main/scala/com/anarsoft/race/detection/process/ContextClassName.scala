package com.anarsoft.race.detection.process

import com.anarsoft.race.detection.process.field.FieldIdMap

trait ContextClassName {
   
  def  classIdMap  : FieldIdMap;
   var classId2Name : Array[String] = null;
}