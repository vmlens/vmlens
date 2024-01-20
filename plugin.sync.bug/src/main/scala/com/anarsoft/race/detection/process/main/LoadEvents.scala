package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.process.loopAndRunData.RunData

trait LoadEvents {
  def foreach(f: (RunData) => Unit): Unit;
}
