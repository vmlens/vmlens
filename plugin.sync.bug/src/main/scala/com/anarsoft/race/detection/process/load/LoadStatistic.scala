package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.event.load.FilePosition
import com.anarsoft.race.detection.loopAndRunData.LoopAndRunId

import java.io.DataInputStream
import scala.collection.mutable

class LoadStatistic(val stream: DataInputStream) {

  def loadAndClose(): LoopAndRunIdToFilePosition = {
    val loopAndRunIdToFilePosition = new mutable.HashMap[LoopAndRunId, FilePosition]();
    var previous = 0L;

    try {
      while (true) {
        val loopId = stream.readInt();
        val runId = stream.readInt();
        val position = stream.readLong();
        val current = FilePosition(previous, (position - previous).toInt);
        loopAndRunIdToFilePosition.put(LoopAndRunId(loopId, runId), current);
        previous = position;
      }
    } catch {
      case e: java.io.EOFException => ;
    }
    finally {
      stream.close();
    }
    LoopAndRunIdToFilePosition(loopAndRunIdToFilePosition.toMap);
  }

}
