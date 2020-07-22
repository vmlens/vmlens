package com.anarsoft.race.detection.process.result
import com.anarsoft.race.detection.model.result._;

import com.anarsoft.race.detection.model.graph._;
import com.anarsoft.race.detection.model.description._;
import com.anarsoft.race.detection.model.field.ArrayAndFieldOrdinalMap;

trait ContextFieldAndArrayFacade {
  
    
   def arrayAndFieldOrdinalMap : ArrayAndFieldOrdinalMap;  
   var fieldAndArrayFacade : FieldAndArrayPerMethodFacade = null; 
  
}