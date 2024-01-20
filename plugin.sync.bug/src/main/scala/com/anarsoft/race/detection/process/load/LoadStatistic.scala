package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.event.load.FilePosition

import java.io.DataInputStream
import scala.collection.mutable.HashMap;

class LoadStatistic(val stream: DataInputStream) {

  def loadAndClose(): SlidingWindowIdToFilePosition = {
    val slidingWindowIdToFilePosition = new HashMap[Int, FilePosition]();
    var previous = 0L;
    var maxSlidingWindow = -1;
    try {
      while (true) {
        val slidingWindow = stream.readInt();
        val position = stream.readLong();
        val current = FilePosition(previous, (position - previous).toInt);
        slidingWindowIdToFilePosition.put(slidingWindow, current);
        maxSlidingWindow = Math.max(maxSlidingWindow, slidingWindow);
        previous = position;
      }
    } catch {
      case e: java.io.EOFException => ;
    }
    finally {
      stream.close();
    }
    SlidingWindowIdToFilePosition(maxSlidingWindow, slidingWindowIdToFilePosition.toMap);
  }

}
