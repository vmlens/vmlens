package com.anarsoft.race.detection.event.loadAndDistribute

import com.anarsoft.race.detection.event.distribute.{DistributeEvents, EventWithLoopAndRunId, LoadedEventContext}
import com.anarsoft.race.detection.event.gen.MethodDeserializer
import com.anarsoft.race.detection.event.load.{DeserializeEvents, DeserializeStrategy, FilePosition, LoadOneFilePosition}
import com.anarsoft.race.detection.event.method.{LoadedMethodEvent, LoadedMethodEventContext}
import com.anarsoft.race.detection.process.load.LoadAndDistributeOneFilePosition
import com.anarsoft.race.detection.process.loopAndRunData.RunDataListBuilder
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow.EVENT_FILE_POSTFIX

import java.io.RandomAccessFile
import java.nio.file.{Files, Path}


class LoadAndDistributeOneFilePositionImpl[EVENT <: EventWithLoopAndRunId](val loadOneFilePosition: LoadOneFilePosition,
                                                                           val deserializeEvents: DeserializeEvents[EVENT],
                                                                           val deserializeStrategy: DeserializeStrategy[EVENT],
                                                                           val distributeEvents: DistributeEvents[EVENT],
                                                                           val createContext: () => LoadedEventContext[EVENT])
  extends LoadAndDistributeOneFilePosition {

  override def load(filePosition: FilePosition, builder: RunDataListBuilder): Unit = {
    val data = loadOneFilePosition.load(filePosition);
    val list = deserializeEvents.deserialize(data, deserializeStrategy);
    distributeEvents.distribute(list, createContext, builder)
  }

  override def close(): Unit = {
    loadOneFilePosition.close();
  }
}

object LoadAndDistributeOneFilePositionImpl {

  def method(dir: Path): DirTypeNameAndLoadAndDistributeOneFilePosition = create[LoadedMethodEvent](dir, "method", new MethodDeserializer(),
    () => {
      new LoadedMethodEventContext()
    });


  private def create[EVENT <: EventWithLoopAndRunId](dir: Path, typeName: String, deserializeStrategy: DeserializeStrategy[EVENT],
                                                     createContext: () => LoadedEventContext[EVENT]): DirTypeNameAndLoadAndDistributeOneFilePosition = {
    val loadOneFilePosition = new LoadOneFilePosition(new RandomAccessFile(dir.resolve(typeName + EVENT_FILE_POSTFIX).toFile, "r"))
    val deserializeEvents = new DeserializeEvents[EVENT]();
    val distributeEvents = new DistributeEvents[EVENT]();
    DirTypeNameAndLoadAndDistributeOneFilePosition(dir, typeName,
      new LoadAndDistributeOneFilePositionImpl(loadOneFilePosition, deserializeEvents, deserializeStrategy, distributeEvents, createContext));
  }


}