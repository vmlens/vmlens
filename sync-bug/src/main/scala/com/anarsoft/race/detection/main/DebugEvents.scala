package com.anarsoft.race.detection.main

import com.anarsoft.race.detection.debug.{DescriptionCallbackForDebug, RunDataListBuilderForDebug}
import com.anarsoft.race.detection.event.nonvolatile.LoadedNonVolatileEvent
import com.anarsoft.race.detection.process.load.{EventFilterNoOp, LoadRunsImpl}
import com.anarsoft.race.detection.process.loadAgentLog.LoadAgentLog
import com.anarsoft.race.detection.process.loadDescription.LoadDescriptionImpl

import java.io.PrintStream
import java.nio.file.{Path, Paths}


class DebugEvents(val dir : Path) {
  
  def process(): Unit = {
    val stream = new PrintStream("debug.text");
    new LoadAgentLog(dir).load(stream);
    new LoadDescriptionImpl(dir).load(new DescriptionCallbackForDebug(stream));
    val loadEvents = new LoadRunsFactory().create(dir,new EventFilterNoOp[LoadedNonVolatileEvent]).asInstanceOf[LoadRunsImpl]
    val runDataListBuilderForDebug = new RunDataListBuilderForDebug(stream);
    loadEvents.load(runDataListBuilderForDebug)
   
    stream.close();
  }

}

object DebugEvents {

  def main(args: Array[String]): Unit = {
    val dir = Paths.get(args(0));
    new DebugEvents(dir).process();
  }

}
