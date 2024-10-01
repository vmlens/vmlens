package com.anarsoft.race.detection.main

import com.anarsoft.race.detection.event.loadAndDistribute.DirTypeNameAndLoadAndDistributeOneFilePosition
import com.anarsoft.race.detection.event.loadAndDistribute.LoadAndDistributeOneFilePositionImpl.{interleave, syncAction}
import com.anarsoft.race.detection.process.load.{LoadAndDistributeOneFilePosition, LoadRunsImpl, LoadStatisticAndDistributeOneFilePosition}
import com.anarsoft.race.detection.process.main.LoadRuns
import com.vmlens.trace.agent.bootstrap.event.StreamRepository.{INTERLEAVE, SYNC_ACTIONS}

import java.nio.file.Path
import scala.collection.mutable.ArrayBuffer

class LoadRunsFactory {

  def create(dir: Path): LoadRuns = {
    val arrayBuffer = new ArrayBuffer[LoadStatisticAndDistributeOneFilePosition]();
    arrayBuffer.append(create(dir, SYNC_ACTIONS, (path, name) => syncAction(path, name)));
    arrayBuffer.append(create(dir, INTERLEAVE, (path, name) => interleave(path, name)));
    new LoadRunsImpl(arrayBuffer.toList);
  }

  private def create(dir: Path, name: String,
                     create: (Path, String) => LoadAndDistributeOneFilePosition): LoadStatisticAndDistributeOneFilePosition = {
    LoadStatisticAndDistributeOneFilePosition(DirTypeNameAndLoadAndDistributeOneFilePosition(dir, name,
      create(dir, name)))
  }
}
