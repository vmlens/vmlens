package com.anarsoft.race.detection.process.result

import com.anarsoft.race.detection.model.description.ThreadNames;
import scala.collection.mutable.HashMap


trait ContextReadThreadNames {
  
  
  var threadNames  : ThreadNames = null;
  var loopId2Name : HashMap[Int,String]  = null;
  
}