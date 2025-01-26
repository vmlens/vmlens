package com.anarsoft.race.detection.main

import com.anarsoft.race.detection.debug.{DescriptionBuilderForDebug, RunDataListBuilderForDebug}
import com.anarsoft.race.detection.process.load.LoadRunsImpl
import com.anarsoft.race.detection.process.loadAgentLog.LoadAgentLog
import com.anarsoft.race.detection.process.loadDescription.LoadDescriptionImpl

import java.nio.file.Paths

object DebugEvents {

  def main(args: Array[String]): Unit = {
    val dir = Paths.get(args(0));
    val loadEvents = new LoadRunsFactory().create(dir).asInstanceOf[LoadRunsImpl]

    val runDataListBuilderForDebug = new RunDataListBuilderForDebug();

    loadEvents.load(runDataListBuilderForDebug)

    new LoadDescriptionImpl(dir).load(new DescriptionBuilderForDebug());
    new LoadAgentLog(dir).load(System.out);
  }

}
