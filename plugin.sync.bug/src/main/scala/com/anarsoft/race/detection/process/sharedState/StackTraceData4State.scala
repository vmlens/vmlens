package com.anarsoft.race.detection.process.sharedState

import org.roaringbitmap.RoaringBitmap;
import com.anarsoft.race.detection.model.result.StackTraceOrdinal
import com.anarsoft.race.detection.model.result.StackTraceGraph
import com.anarsoft.race.detection.model.description.MethodModel

class StackTraceData4State(val methodModel : MethodModel) extends AbstractStackTraceData {
  
   val packageIdSet          = new RoaringBitmap;
   val memoryAggregateIdSet  = new RoaringBitmap;

  
   //def processChildsOrState() =    ownerName.isEmpty &&  !  isStateless;
   
 
}