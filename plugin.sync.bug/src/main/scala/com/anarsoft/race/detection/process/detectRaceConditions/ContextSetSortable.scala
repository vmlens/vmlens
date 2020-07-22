package com.anarsoft.race.detection.process.detectRaceConditions

import com.anarsoft.race.detection.process.partialOrder.PartialOrder
import com.anarsoft.race.detection.process.nonVolatileField.ContextNonVolatileFields
import scala.collection.mutable.HashSet;


trait ContextSetSortable extends ContextNonVolatileFields {
  
   def  partialOrder : PartialOrder;
  
  
}