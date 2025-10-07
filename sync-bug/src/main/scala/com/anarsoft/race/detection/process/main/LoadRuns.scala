package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.rundata.RunData

trait LoadRuns {
  def foreach(f: (RunData) => Unit): Unit;
}
