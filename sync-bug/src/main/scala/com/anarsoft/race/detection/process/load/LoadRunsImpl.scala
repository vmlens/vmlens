package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.rundata.{LoopAndRunId, RunData, RunDataListBuilder, RunDataListBuilderImpl}
import com.anarsoft.race.detection.process.main.LoadRuns

import scala.collection.mutable


class LoadRunsImpl(val loadEventFiles : List[LoadAndDistributeEvents]) extends LoadRuns {

  override def foreach(f: RunData => Unit): Unit = {
    val builder = new RunDataListBuilderImpl();
    load(builder);
    val runDataList = builder.build();
    runDataList.foreach(f);
  }

  def load(builder: RunDataListBuilder): Unit = {
    for(loader <- loadEventFiles) {
      loader.load(builder);
    }
  }
}
