package com.anarsoft.race.detection.reportbuilder

import com.vmlens.report.builder.{LoopAndRun, ReportBuilder}
import com.vmlens.report.element.{RunElement, StacktraceLeaf, TestLoop}

import java.util
import scala.collection.mutable.ArrayBuffer

class ReportBuilderMock extends ReportBuilder {

  val loopAndRunList = new ArrayBuffer[LoopAndRun]()
  val stacktraceLeafList = new ArrayBuffer[StacktraceLeaf]()

  override def addLoopAndRun(testLoop: TestLoop, run: util.List[RunElement]): Unit = {
    loopAndRunList.append(new LoopAndRun(testLoop, run));
  }


}
