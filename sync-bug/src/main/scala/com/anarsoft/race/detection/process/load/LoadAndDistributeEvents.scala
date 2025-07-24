package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.loopAndRunData.RunDataListBuilder

trait LoadAndDistributeEvents {
  def load(builder: RunDataListBuilder): Unit;
}
