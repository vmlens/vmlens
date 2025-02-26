package com.anarsoft.race.detection.main

import com.anarsoft.race.detection.event.loadanddistribute.DirTypeNameAndLoadAndDistributeOneFilePosition
import com.anarsoft.race.detection.event.loadanddistribute.LoadAndDistributeOneFilePositionImpl.{control, interleave, method, nonVolatile}
import com.anarsoft.race.detection.process.load.{LoadAndDistributeOneFilePosition, LoadRunsImpl, LoadStatisticAndDistributeOneFilePosition}
import com.anarsoft.race.detection.process.main.LoadRuns
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository.{CONTROL, INTERLEAVE, METHOD_EVENTS, NON_VOLATILE}

import java.nio.file.Path
import scala.collection.mutable.ArrayBuffer


class LoadRunsFactory {

  def create(dir: Path): LoadRuns = {
    val arrayBuffer = new ArrayBuffer[LoadStatisticAndDistributeOneFilePosition]();
    arrayBuffer.append(create(dir, INTERLEAVE, (path, name) => interleave(path, name)));
    arrayBuffer.append(create(dir, CONTROL, (path, name) => control(path, name)));
    arrayBuffer.append(create(dir, METHOD_EVENTS, (path, name) => method(path, name)));
    arrayBuffer.append(create(dir, NON_VOLATILE, (path, name) => nonVolatile(path, name)));
    new LoadRunsImpl(arrayBuffer.toList);
  }

  private def create(dir: Path, name: String,
                     create: (Path, String) => LoadAndDistributeOneFilePosition): LoadStatisticAndDistributeOneFilePosition = {
    LoadStatisticAndDistributeOneFilePosition(DirTypeNameAndLoadAndDistributeOneFilePosition(dir, name,
      create(dir, name)))
  }
}
