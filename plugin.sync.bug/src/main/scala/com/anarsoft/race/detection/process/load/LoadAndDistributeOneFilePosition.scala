package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.event.load.FilePosition
import com.anarsoft.race.detection.loopAndRunData.RunDataListBuilder

trait LoadAndDistributeOneFilePosition {
  def load(filePosition: FilePosition, builder: RunDataListBuilder): Unit;

  def close(): Unit;
}
