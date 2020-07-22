package com.anarsoft.race.detection.process.nonVolatileField

import java.util.ArrayList

class NonVolatileListCollection[ARRAY,FIELD,STATIC] {
  
  
  val arrayList = new ArrayList[ARRAY]();
  val nonVolatileFieldList = new ArrayList[FIELD]();
  val nonVolatileFieldListStatic = new ArrayList[STATIC]();
  
}