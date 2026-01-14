package com.anarsoft.race.detection.main

import com.anarsoft.race.detection.event.automatictest.{AutomaticTestContext, LoadedAutomaticTestEvent}
import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId
import com.anarsoft.race.detection.event.gen.*
import com.anarsoft.race.detection.event.load.DeserializeStrategy
import com.anarsoft.race.detection.event.control.{LoadedControlContext, LoadedControlEvent}
import com.anarsoft.race.detection.event.interleave.{LoadedInterleaveActionContext, LoadedInterleaveActionEvent}
import com.anarsoft.race.detection.event.method.{LoadedMethodEvent, LoadedMethodEventContext}
import com.anarsoft.race.detection.event.nonvolatile.{LoadedNonVolatileEvent, LoadedNonVolatileEventContext}
import com.anarsoft.race.detection.rundata.{LoopAndRunId, RunDataListBuilder}
import com.anarsoft.race.detection.main.LoadRunsFactory.{automaticTest, control, interleave, method, nonVolatile}
import com.anarsoft.race.detection.process.load.{EventFilter, EventFilterNoOp, LoadAndDistributeEvents, LoadAndDistributeEventsImpl, LoadAndDistributeEventsNoop, LoadRunsImpl}
import com.anarsoft.race.detection.process.main.LoadRuns
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository.{CONTROL, INTERLEAVE, METHOD_EVENTS, NON_VOLATILE, AUTOMATIC_TEST}
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId.EVENT_FILE_POSTFIX
import com.anarsoft.race.detection.event.distribute.LoadedEventContext
import com.anarsoft.race.detection.event.distribute.DistributeEvents

import java.nio.file.Path
import java.nio.file.Files
import scala.collection.mutable.ArrayBuffer


class LoadRunsFactory {

  def create(dir: Path,filter : EventFilter[LoadedNonVolatileEvent]): LoadRuns = {
    val arrayBuffer = new ArrayBuffer[LoadAndDistributeEvents]();
    arrayBuffer.append(nonVolatile(getPath(dir, NON_VOLATILE),filter));
    arrayBuffer.append(method(getPath(dir, METHOD_EVENTS)));
    arrayBuffer.append(interleave(getPath(dir, INTERLEAVE)));
    arrayBuffer.append(control(getPath(dir, CONTROL)));
    arrayBuffer.append(automaticTest(getPath(dir, AUTOMATIC_TEST)));
    new LoadRunsImpl(arrayBuffer.toList);
  }

  def getPath(dir : Path, fileName : String)  :  Path = dir.resolve(fileName + EVENT_FILE_POSTFIX);
  
}

object LoadRunsFactory {

  def nonVolatile(filePath : Path, filter : EventFilter[LoadedNonVolatileEvent]) : LoadAndDistributeEvents = create[LoadedNonVolatileEvent](filePath : Path,
    new NonVolatileDeSerializer(),
    () => {
      new LoadedNonVolatileEventContext()
    },filter);

  def method(filePath : Path): LoadAndDistributeEvents = create[LoadedMethodEvent](filePath ,
    new MethodDeSerializer(),
    () => {
      new LoadedMethodEventContext()
    }
   );

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

  def automaticTest(filePath: Path): LoadAndDistributeEvents = create[LoadedAutomaticTestEvent](filePath,
    new AutomaticTestDeSerializer(),
    () => {
      new AutomaticTestContext()
    });


  private def create[EVENT <: EventWithLoopAndRunId](filePath : Path,
                                                     deserializeStrategy: DeserializeStrategy[EVENT],
                                                     createContext: () => LoadedEventContext[EVENT]): LoadAndDistributeEvents =
    create[EVENT](filePath,deserializeStrategy,createContext,new EventFilterNoOp[EVENT]);

  private def create[EVENT <: EventWithLoopAndRunId](filePath : Path,
    deserializeStrategy: DeserializeStrategy[EVENT],
    createContext: () => LoadedEventContext[EVENT], filter : EventFilter[EVENT]): LoadAndDistributeEvents = {
    if (!Files.exists(filePath)) {
      new LoadAndDistributeEventsNoop();
    }
    else {
      val distributeEvents = new DistributeEvents[EVENT](createContext);
      new LoadAndDistributeEventsImpl(filePath, deserializeStrategy, distributeEvents,filter);
    }
  }

}
