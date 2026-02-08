package com.anarsoft.race.detection.debug

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.event.automatictest.LoadedAutomaticTestEvent
import com.anarsoft.race.detection.event.control.ControlEvent
import com.anarsoft.race.detection.groupinterleave.{GroupInterleaveElement, GroupInterleaveElementSyncActionImpl, GroupInterleaveElementThreadOperationImpl}
import com.anarsoft.race.detection.groupnonvolatile.{GroupNonVolatileElement, GroupNonVolatileElementImpl}
import com.anarsoft.race.detection.rundata.{LoopAndRunId, RunDataListBuilder}

import java.io.PrintStream
import java.util

class RunDataListBuilderForDebug(stream : PrintStream) extends RunDataListBuilder {

  override def addControlEvents(loopAndRunId: LoopAndRunId, interleaveEventList: List[ControlEvent]): Unit = {
    for (elem <- interleaveEventList) {
      stream.println(elem.toString);
    }
  }

  override def addMethodEvents(loopAndRunId: LoopAndRunId, methodEventList: util.List[MethodEvent]): Unit = {
    val iter = methodEventList.iterator();
    while (iter.hasNext) {
      val elem = iter.next();
      stream.println(elem.toString);
    }

  }

  override def addNonVolatileElements(loopAndRunId: LoopAndRunId, nonVolatileElements: List[GroupNonVolatileElement]): Unit = {
    for (elem <- nonVolatileElements) {
      for (event <- elem.asInstanceOf[GroupNonVolatileElementImpl[_]].eventArray) {
        stream.println(event.toString)
      }
    }
  }

  override def addSyncActionElements(loopAndRunId: LoopAndRunId, syncActionElements: List[GroupInterleaveElement]): Unit = {
    for (elem <- syncActionElements) {
      if (elem.isInstanceOf[GroupInterleaveElementSyncActionImpl[_]]) {
        for (event <- elem.asInstanceOf[GroupInterleaveElementSyncActionImpl[_]].eventArray) {
          stream.println(event.toString)
        }
      } else {
        for (event <- elem.asInstanceOf[GroupInterleaveElementThreadOperationImpl[_]].eventArray) {
          stream.println(event.toString)
        }
      }
    }
  }

  override def addAutomaticTestElements(loopAndRunId: LoopAndRunId, automaticTestList: util.List[LoadedAutomaticTestEvent]): Unit =  {
    
  }
}
