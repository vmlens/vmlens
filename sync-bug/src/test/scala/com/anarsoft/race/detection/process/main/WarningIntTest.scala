package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.event.control.ControlEvent
import com.anarsoft.race.detection.event.gen.LoopWarningEventGen
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunDataListBuilderImpl}
import com.anarsoft.race.detection.process.run.ProcessRunImpl
import com.vmlens.report.assertion.{OnDescriptionAndLeftBeforeRightNoOp, OnEventNoOp}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable.ArrayBuffer

class WarningIntTest extends AnyFlatSpec with Matchers {

  "when a warning is in the list a warning id" should " be contained in run result" in {
    // Given
    val runDataListBuilderImpl = new RunDataListBuilderImpl();
    val controlEventList   = new ArrayBuffer[ControlEvent]();
    controlEventList.append(new LoopWarningEventGen(5,7,22));
    val id =  LoopAndRunId(5,7);
    runDataListBuilderImpl.addControlEvents(id, controlEventList.toList);
    val loadRunsMock = new LoadRunsMock(runDataListBuilderImpl.build());

    // When
    val result = new ProcessEvents(loadRunsMock,new ProcessRunImpl(new OnDescriptionAndLeftBeforeRightNoOp(), new OnEventNoOp())).process();

    // Then
    result(0).runResult.warningIdList should be (Set(22));

  }
}
