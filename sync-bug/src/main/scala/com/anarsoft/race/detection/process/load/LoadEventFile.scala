package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.loopAndRunData.RunDataListBuilder

trait LoadEventFile {
  def load(builder: RunDataListBuilder): Unit;
}
