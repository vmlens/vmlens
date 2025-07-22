package com.anarsoft.race.detection.main

import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId
import com.anarsoft.race.detection.event.gen.*
import com.anarsoft.race.detection.event.load.DeserializeStrategy
import com.anarsoft.race.detection.event.control.{LoadedControlEvent, LoadedControlContext}
import com.anarsoft.race.detection.event.interleave.{LoadedInterleaveActionContext, LoadedInterleaveActionEvent}
import com.anarsoft.race.detection.event.method.{LoadedMethodEventContext, LoadedMethodEvent}
import com.anarsoft.race.detection.event.nonvolatile.{LoadedNonVolatileEvent, LoadedNonVolatileEventContext}
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunDataListBuilder}
import com.anarsoft.race.detection.main.LoadRunsFactory.{nonVolatile, method, interleave, control}
import com.anarsoft.race.detection.process.load.{LoadAndDistributeEvents, LoadAndDistributeEventsImpl, LoadAndDistributeEventsNoop, LoadRunsImpl}
import com.anarsoft.race.detection.process.main.LoadRuns
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository.{CONTROL, INTERLEAVE, METHOD_EVENTS, NON_VOLATILE}
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId.EVENT_FILE_POSTFIX
import com.anarsoft.race.detection.event.distribute.LoadedEventContext
import com.anarsoft.race.detection.event.distribute.DistributeEvents

import java.nio.file.Path
import java.nio.file.Files
import scala.collection.mutable.ArrayBuffer


class LoadRunsFactory {

  def create(dir: Path): LoadRuns = {
    val arrayBuffer = new ArrayBuffer[LoadAndDistributeEvents]();
    arrayBuffer.append(nonVolatile(getPath(dir, NON_VOLATILE)));
    arrayBuffer.append(method(getPath(dir, METHOD_EVENTS)));
    arrayBuffer.append(interleave(getPath(dir, INTERLEAVE)));
    arrayBuffer.append(control(getPath(dir, CONTROL)));
    new LoadRunsImpl(arrayBuffer.toList);
  }

  def getPath(dir : Path, fileName : String)  :  Path = dir.resolve(fileName + EVENT_FILE_POSTFIX);


}

object LoadRunsFactory {

  def nonVolatile(filePath : Path) : LoadAndDistributeEvents = create[LoadedNonVolatileEvent](filePath : Path,
    new NonVolatileDeSerializer(),
    () => {
      new LoadedNonVolatileEventContext()
    });


  def method(filePath : Path): LoadAndDistributeEvents = create[LoadedMethodEvent](filePath ,
    new MethodDeSerializer(),
    () => {
      new LoadedMethodEventContext()
    });

  def interleave(filePath : Path): LoadAndDistributeEvents = create[LoadedInterleaveActionEvent](filePath,
    new InterleaveDeSerializer(),
    () => {
      new LoadedInterleaveActionContext()
    });

  def control(filePath : Path): LoadAndDistributeEvents = create[LoadedControlEvent](filePath ,
    new ControlDeSerializer(),
    () => {
      new LoadedControlContext()
    });


  private def create[EVENT <: EventWithLoopAndRunId](filePath : Path,
                                                     deserializeStrategy: DeserializeStrategy[EVENT],
                                                     createContext: () => LoadedEventContext[EVENT]): LoadAndDistributeEvents = {
    if (!Files.exists(filePath)) {
      new LoadAndDistributeEventsNoop();
    }
    else {
      val distributeEvents = new DistributeEvents[EVENT](createContext);
      new LoadAndDistributeEventsImpl(filePath, deserializeStrategy, distributeEvents);
    }

  }
}
