package com.anarsoft.race.detection.event.loadAndDistribute

import com.anarsoft.race.detection.event.distribute.{DistributeEvents, EventWithLoopAndRunId, LoadedEventContext}
import com.anarsoft.race.detection.event.gen.{InterleaveDeSerializer, MethodDeSerializer, SyncActionsDeSerializer}
import com.anarsoft.race.detection.event.interleave.{LoadedInterleaveContext, LoadedInterleaveEvent}
import com.anarsoft.race.detection.event.load.{DeserializeEvents, DeserializeStrategy, FilePosition, LoadOneFilePosition}
import com.anarsoft.race.detection.event.method.{LoadedMethodEvent, LoadedMethodEventContext}
import com.anarsoft.race.detection.event.syncAction.{LoadedSyncActionContext, LoadedSyncActionEvent}
import com.anarsoft.race.detection.loopAndRunData.RunDataListBuilder
import com.anarsoft.race.detection.process.load.LoadAndDistributeOneFilePosition
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithLoopIdAndRunId.EVENT_FILE_POSTFIX

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

  def method(dir: Path, typeName: String): LoadAndDistributeOneFilePosition = create[LoadedMethodEvent](dir, typeName,
    new MethodDeSerializer(),
    () => {
      new LoadedMethodEventContext()
    });

  def syncAction(dir: Path, typeName: String): LoadAndDistributeOneFilePosition = create[LoadedSyncActionEvent](dir, typeName,
    new SyncActionsDeSerializer(),
    () => {
      new LoadedSyncActionContext()
    });

  def interleave(dir: Path, typeName: String): LoadAndDistributeOneFilePosition = create[LoadedInterleaveEvent](dir, typeName,
    new InterleaveDeSerializer(),
    () => {
      new LoadedInterleaveContext()
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