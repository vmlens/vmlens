package com.anarsoft.race.detection.event.loadAndDistribute

import com.anarsoft.race.detection.event.load.FilePosition
import com.anarsoft.race.detection.loopAndRunData.RunDataListBuilder
import com.anarsoft.race.detection.process.load.LoadAndDistributeOneFilePosition

class LoadAndDistributeOneFilePositionNoOp extends LoadAndDistributeOneFilePosition {
  override def load(filePosition: FilePosition, builder: RunDataListBuilder): Unit = {

  }

  override def close(): Unit = {

  }
}
