package com.anarsoft.race.detection.process.result

import com.anarsoft.race.detection.model.method._;
import com.anarsoft.race.detection.model.result._;
import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.model.graph._;
import com.anarsoft.race.detection.process.method._;
import org.roaringbitmap.RoaringBitmap;
import scala.collection.mutable.HashSet


trait ContextCreateStackTraceGraph {
  
  

  def stackTraceTree : StackTraceTree ;
  def stackTraceGraphBuilder : StackTraceGraphBuilder;
 
  
  var stackTraceGraph : StackTraceGraph = null;
  def methodId2Ordinal :  ModelKey2OrdinalMap[Int];
  
  def  stackTraceOrdinalWithMonitorOption : Option[RoaringBitmap];
  
  def  threadOrdinalAndStackTraceSet : HashSet[ThreadOrdinalAndStackTrace];
  
  
  
}