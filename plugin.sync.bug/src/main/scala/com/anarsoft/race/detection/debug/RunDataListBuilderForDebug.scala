package com.anarsoft.race.detection.debug

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.groupsyncaction.{SyncActionElementForProcess, SyncActionElementForProcessImpl}
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunDataListBuilder}

import java.util

class RunDataListBuilderForDebug extends RunDataListBuilder {
  override def add(loopAndRunId: LoopAndRunId, methodEventList: util.List[MethodEvent]): Unit = {
    val iter = methodEventList.iterator();
    while (iter.hasNext) {
      println(iter.next());
    }

  }

  override def add(loopAndRunId: LoopAndRunId, syncActionElements: List[SyncActionElementForProcess]): Unit = {
    for (elem <- syncActionElements) {
      for (event <- elem.asInstanceOf[SyncActionElementForProcessImpl[_]].eventArray) {
        println(event);
      }

    }
  }
}
