package com.anarsoft.race.detection.main

import com.anarsoft.race.detection.debug.{DescriptionBuilderForDebug, RunDataListBuilderForDebug}
import com.anarsoft.race.detection.process.load.LoadRunsImpl
import com.anarsoft.race.detection.process.loadAgentLog.LoadAgentLog
import com.anarsoft.race.detection.process.loadDescription.LoadDescriptionImpl
import com.vmlens.report.assertion.{DebugLogger, DebugLoggerToSystemOut}

import java.nio.file.{Path, Paths}


class DebugEvents(val dir : Path,val debugLogger: DebugLogger) {
  
  def process(): Unit = {

    new LoadDescriptionImpl(dir).load(new DescriptionBuilderForDebug(debugLogger));
    val loadEvents = new LoadRunsFactory().create(dir).asInstanceOf[LoadRunsImpl]
    val runDataListBuilderForDebug = new RunDataListBuilderForDebug(debugLogger);
    loadEvents.load(runDataListBuilderForDebug)
    new LoadAgentLog(dir).load(System.out);
  }

}

object DebugEvents {

  def main(args: Array[String]): Unit = {
    val dir = Paths.get(args(0));
    new DebugEvents(dir,new DebugLoggerToSystemOut()).process();
  }

}
