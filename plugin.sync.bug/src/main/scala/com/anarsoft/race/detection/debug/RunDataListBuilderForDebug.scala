package com.anarsoft.race.detection.debug

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.event.interleave.InterleaveEvent
import com.anarsoft.race.detection.groupinterleave.{GroupInterleaveElement, GroupInterleaveElementSyncActionImpl}
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunDataListBuilder}

import java.util

class RunDataListBuilderForDebug extends RunDataListBuilder {

  override def addInterleaveEvents(loopAndRunId: LoopAndRunId, interleaveEventList: List[InterleaveEvent]): Unit = {
    for (elem <- interleaveEventList) {
      println(elem);
    }
  }

  override def addMethodEvents(loopAndRunId: LoopAndRunId, methodEventList: util.List[MethodEvent]): Unit = {
    val iter = methodEventList.iterator();
    while (iter.hasNext) {
      println(iter.next());
    }

  }

  override def addSyncActionElements(loopAndRunId: LoopAndRunId, syncActionElements: List[GroupInterleaveElement]): Unit = {
    for (elem <- syncActionElements) {
      for (event <- elem.asInstanceOf[GroupInterleaveElementSyncActionImpl[_]].eventArray) {
        println(event);
      }

    }
  }
}
