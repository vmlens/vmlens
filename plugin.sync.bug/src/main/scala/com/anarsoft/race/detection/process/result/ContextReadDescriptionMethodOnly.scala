package com.anarsoft.race.detection.process.result

import com.anarsoft.race.detection.model.graph._;
import com.anarsoft.race.detection.model.result._;
import scala.collection.mutable.HashMap


trait ContextReadDescriptionMethodOnly {
  
    def stackTraceGraph : StackTraceGraph;
   def methodId2Ordinal :  ModelKey2OrdinalMap[Int];
 
   
  var   ownerId2Name : HashMap[Int,String] = null; 
  
  
}