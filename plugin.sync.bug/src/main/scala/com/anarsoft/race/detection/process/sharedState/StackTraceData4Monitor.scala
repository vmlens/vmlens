package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result.StackTraceOrdinal
import org.roaringbitmap.RoaringBitmap;

class StackTraceData4Monitor (val isThreadSafe  : Boolean , val stackTraceOrdinal : StackTraceOrdinal) extends AbstractStackTraceData {
  
   val packageIdSet          = new RoaringBitmap;
    var calledMethodContainsMonitor = false;

}