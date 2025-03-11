package com.anarsoft.race.detection.event.loadanddistribute

import com.anarsoft.race.detection.event.control.{LoadedControlContext, LoadedControlEvent}
import com.anarsoft.race.detection.event.distribute.{DistributeEvents, EventWithLoopAndRunId, LoadedEventContext}
import com.anarsoft.race.detection.event.gen.{ControlDeSerializer, InterleaveDeSerializer, MethodDeSerializer, NonVolatileDeSerializer}
import com.anarsoft.race.detection.event.interleave.{LoadedInterleaveActionContext, LoadedInterleaveActionEvent}
import com.anarsoft.race.detection.event.load.{DeserializeEvents, DeserializeStrategy, FilePosition, LoadOneFilePosition}
import com.anarsoft.race.detection.event.method.{LoadedMethodEvent, LoadedMethodEventContext}
import com.anarsoft.race.detection.event.nonvolatile.{LoadedNonVolatileEvent, LoadedNonVolatileEventContext}
import com.anarsoft.race.detection.loopAndRunData.RunDataListBuilder
import com.anarsoft.race.detection.process.load.LoadAndDistributeOneFilePosition
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId.EVENT_FILE_POSTFIX

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

  def nonVolatile(dir: Path, typeName: String): LoadAndDistributeOneFilePosition = create[LoadedNonVolatileEvent](dir, typeName,
    new NonVolatileDeSerializer(),
    () => {
      new LoadedNonVolatileEventContext()
    });


  def method(dir: Path, typeName: String): LoadAndDistributeOneFilePosition = create[LoadedMethodEvent](dir, typeName,
    new MethodDeSerializer(),
    () => {
      new LoadedMethodEventContext()
    });

  def interleave(dir: Path, typeName: String): LoadAndDistributeOneFilePosition = create[LoadedInterleaveActionEvent](dir, typeName,
    new InterleaveDeSerializer(),
    () => {
      new LoadedInterleaveActionContext()
    });

  def control(dir: Path, typeName: String): LoadAndDistributeOneFilePosition = create[LoadedControlEvent](dir, typeName,
    new ControlDeSerializer(),
    () => {
      new LoadedControlContext()
    });


  private def create[EVENT <: EventWithLoopAndRunId](dir: Path, typeName: String, deserializeStrategy: DeserializeStrategy[EVENT],
                                                     createContext: () => LoadedEventContext[EVENT]): LoadAndDistributeOneFilePosition = {
    val path = dir.resolve(typeName + EVENT_FILE_POSTFIX);

    if (!Files.exists(path)) {
      new LoadAndDistributeOneFilePositionNoOp();
    }
    else {
      val loadOneFilePosition = new LoadOneFilePosition(new RandomAccessFile(path.toFile, "r"))
      val deserializeEvents = new DeserializeEvents[EVENT]();
      val distributeEvents = new DistributeEvents[EVENT]();
      new LoadAndDistributeOneFilePositionImpl(loadOneFilePosition, deserializeEvents, deserializeStrategy, distributeEvents, createContext);
    }

  }
}