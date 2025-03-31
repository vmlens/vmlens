package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.createstacktrace.{StacktraceNodeIntermediate, StacktraceNodeRoot}
import com.anarsoft.race.detection.process.main.RunCountAndResult
import com.anarsoft.race.detection.reportbuilder.LoopReportBuilderImpl.toTestResult
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.vmlens.trace.agent.bootstrap.exception.Message.TEST_BLOCKED_MESSAGE

class LoopReportBuilderImplTest extends AnyFlatSpec with Matchers {

  "LoopReportBuilderImpl" should "call addLoopAndRun and addStacktraceLeaf on report builder" in {
    // Given
    val root = new StacktraceNodeRoot(1);
    val leaf = StacktraceNodeIntermediate(5, root);

    val reportBuilderMock = new ReportBuilderMock();
    val loopReportBuilderImpl = new LoopReportBuilderImpl(reportBuilderMock);
    val eventForRunElements = List(new EventForReportElementGuineaPig(1, 1, 1, 6, Some(leaf), new RunElementTypeGuineaPig()));
    val runResultMocks = new RunResultMock(eventForRunElements, false,  0 , Set());

    // When
    loopReportBuilderImpl.addRunResult(RunCountAndResult(5, runResultMocks))
    val descriptionBuilderForReport = loopReportBuilderImpl.build();

    // Then
    reportBuilderMock.loopAndRunList.size should be(1)
    reportBuilderMock.stacktraceLeafList.size should be(0)
  }

  "toTestResult" should "display the correct text" in {

    toTestResult(new RunResultMock(List(), true, 5 , Set(TEST_BLOCKED_MESSAGE.id()))).text() should be("Failure, data race, test blocked")
    toTestResult(new RunResultMock(List(), false, 0, Set())).text() should be("Success")
    toTestResult(new RunResultMock(List(), false, 3, Set())).text() should be("Data race")

  }
  
}
