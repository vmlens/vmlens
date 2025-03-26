package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.loopAndRunData.RunData

class LoadRunsMock(private val  runDataList : List[RunData]) extends LoadRuns {

  override def foreach(f: RunData => Unit): Unit = runDataList.foreach[Unit](f);
  
}
