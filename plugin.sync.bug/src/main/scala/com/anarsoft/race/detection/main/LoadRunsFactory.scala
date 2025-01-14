package com.anarsoft.race.detection.main

import com.anarsoft.race.detection.event.loadanddistribute.DirTypeNameAndLoadAndDistributeOneFilePosition
import com.anarsoft.race.detection.event.loadanddistribute.LoadAndDistributeOneFilePositionImpl.{control, interleave}
import com.anarsoft.race.detection.process.load.{LoadAndDistributeOneFilePosition, LoadRunsImpl, LoadStatisticAndDistributeOneFilePosition}
import com.anarsoft.race.detection.process.main.LoadRuns
import com.vmlens.trace.agent.bootstrap.event.StreamRepository.{CONTROL, INTERLEAVE}

import java.nio.file.Path
import scala.collection.mutable.ArrayBuffer


class LoadRunsFactory {

  def create(dir: Path): LoadRuns = {
    val arrayBuffer = new ArrayBuffer[LoadStatisticAndDistributeOneFilePosition]();
    arrayBuffer.append(create(dir, INTERLEAVE, (path, name) => interleave(path, name)));
    arrayBuffer.append(create(dir, CONTROL, (path, name) => control(path, name)));
    new LoadRunsImpl(arrayBuffer.toList);
  }

  private def create(dir: Path, name: String,
                     create: (Path, String) => LoadAndDistributeOneFilePosition): LoadStatisticAndDistributeOneFilePosition = {
    LoadStatisticAndDistributeOneFilePosition(DirTypeNameAndLoadAndDistributeOneFilePosition(dir, name,
      create(dir, name)))
  }
}
