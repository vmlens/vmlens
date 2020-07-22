package com.anarsoft.race.detection.process.method

import scala.collection.mutable.HashMap;
import scala.collection.mutable.ArrayBuffer;
import com.anarsoft.race.detection.process.read._;
import java.util.ArrayList;
import com.anarsoft.race.detection.model.method.StackTraceElement2Id;
import com.anarsoft.race.detection.model.description.ThreadNames;
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.model.graph.ModelKey2OrdinalMap
import com.anarsoft.race.detection.model.result.MethodOrdinal
//import com.anarsoft.race.detection.process.filter.FilterList


trait ContextMethodData  {
  
   var methodEventStreams  :  StreamAndStreamStatistic[ApplyMethodEventVisitor] = null;
  
   var currentReadMethodEvents :   ArrayList[MethodEvent] = null;
   
   var methodFlow : MethodFlow = null;
   var stackTraceTree : StackTraceTree  = null;
  
   
   var threadOrdinalAndStackTraceSet = new HashSet[ThreadOrdinalAndStackTrace]
   
   var threadId2StackTraceForestPerThreadBuilder : HashMap[Long,StackTraceForestPerThreadBuilder] = null;
   
   var stackTraceElement2Id : StackTraceElement2Id = null;
   
 //  var methodIdAndThreadIdSet : HashSet[MethodIdAndThreadId] = null;

   
   def threadNames  : ThreadNames;
   
    
   def methodId2Ordinal :  ModelKey2OrdinalMap[Int]; 
    
//   var parallizeMethodEvents : ArrayList[ParallizedMethodEnterEvent] = null;
   
  // def  filterList : FilterList;
   
   
}