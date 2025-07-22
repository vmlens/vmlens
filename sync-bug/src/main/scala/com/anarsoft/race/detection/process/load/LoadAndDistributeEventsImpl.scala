package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.event.distribute.{DistributeEvents, EventWithLoopAndRunId}
import com.anarsoft.race.detection.event.load.DeserializeStrategy
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunDataListBuilder}

import java.io.{DataInputStream, EOFException, FileInputStream}
import java.nio.file.Path
import java.util
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class LoadAndDistributeEventsImpl[EVENT <: EventWithLoopAndRunId](val filePath : Path,
                                                                  val deserializeStrategy : DeserializeStrategy[EVENT],
                                                                  val distribute : DistributeEvents[EVENT])
      extends LoadAndDistributeEvents {
  
  def load(builder: RunDataListBuilder): Unit = {
    distribute.distribute(loadEvents(),builder);
  }

  def loadEvents() : util.LinkedList[EVENT] = {
    val eventList = new util.LinkedList[EVENT]();
    val dataInputStream = new DataInputStream(new FileInputStream(filePath.toFile));
    try {
      while (true) {
        val event = deserializeStrategy.deSerializeJavaEvent(dataInputStream);
        eventList.add(event);
      }
    } catch {
      case _: EOFException =>

    } finally {
      dataInputStream.close()
    }
    eventList;
  }
  
}
