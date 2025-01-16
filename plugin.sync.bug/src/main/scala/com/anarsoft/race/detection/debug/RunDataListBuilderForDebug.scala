package com.anarsoft.race.detection.debug

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.event.control.ControlEvent
import com.anarsoft.race.detection.groupinterleave.{GroupInterleaveElement, GroupInterleaveElementSyncActionImpl, GroupInterleaveElementThreadOperationImpl}
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunDataListBuilder}

import java.util

class RunDataListBuilderForDebug extends RunDataListBuilder {

  override def addControlEvents(loopAndRunId: LoopAndRunId, interleaveEventList: List[ControlEvent]): Unit = {
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
      if (elem.isInstanceOf[GroupInterleaveElementSyncActionImpl[_]]) {
        for (event <- elem.asInstanceOf[GroupInterleaveElementSyncActionImpl[_]].eventArray) {
          println(event);
        }
      } else {
        for (event <- elem.asInstanceOf[GroupInterleaveElementThreadOperationImpl[_]].eventArray) {
          println(event);
        }
      }




    }
  }
}
