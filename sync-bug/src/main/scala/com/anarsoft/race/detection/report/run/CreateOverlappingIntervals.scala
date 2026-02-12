package com.anarsoft.race.detection.report.run

import scala.collection.mutable

class CreateOverlappingIntervals {

  def createOverlappingIntervals(startPositionList :  mutable.ArrayBuffer[Interval]): mutable.ArrayBuffer[Interval] = {
    val result = new mutable.ArrayBuffer[Interval];
    val sorted =  startPositionList.sortBy( interval => interval.start  )
    var current = sorted(0);
    for ( index <- 1 until  sorted.size ) {
      val next = sorted(index)
      if (next.start <= current.end)  {
        current =  Interval(current.start,  Math.max(current.end, next.end)
        );
      } else {
        result.append(current);
        current = next;
      }
    }
    result.append(current);
    result;
  }
  
}
