package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.createstacktrace.{StacktraceNodeIntermediate, StacktraceNodeRoot}
import com.anarsoft.race.detection.process.main.RunCountAndResult
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class LoopReportBuilderImplTest extends AnyFlatSpec with Matchers {

  "LoopReportBuilderImpl" should "call addLoopAndRun and addStacktraceLeaf on report builder" in {
    // Given
    val root = new StacktraceNodeRoot(1);
    val leaf = StacktraceNodeIntermediate(5, root);

    val reportBuilderMock = new ReportBuilderMock();
    val loopReportBuilderImpl = new LoopReportBuilderImpl(reportBuilderMock);
    val eventForRunElements = List(new EventForReportElementGuineaPig(1, 1, 1, 6, Some(leaf), new OperationTextFactoryGuineaPig()));
    val runResultMocks = new RunResultMock(eventForRunElements, false, 0);

    // When
    loopReportBuilderImpl.addRunResult(RunCountAndResult(5, runResultMocks))
    val descriptionBuilderForReport = loopReportBuilderImpl.build();

    // Then
    reportBuilderMock.loopAndRunList.size should be(1)
    reportBuilderMock.stacktraceLeafList.size should be(0)
  }
}
