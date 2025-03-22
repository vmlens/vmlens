package com.anarsoft.race.detection.reportbuilder


import com.anarsoft.race.detection.process.main.{DescriptionBuilderForReport, LoopReportBuilder, RunCountAndResult}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.report.builder.ReportBuilder
import com.vmlens.report.element.*

import java.util
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class LoopReportBuilderImpl(reportBuilder: ReportBuilder) extends LoopReportBuilder {

  private val runCountAndResultList = new ArrayBuffer[RunCountAndResult]();

  override def addRunResult(runResult: RunCountAndResult): Unit = {
    runCountAndResultList.append(runResult)
  }

  def build(): DescriptionBuilderForReport = {
    val stacktraceLeafsMap = new mutable.HashMap[StacktraceNode, StacktraceLeaf]();

    var failures = 0;
    var dataRaces = 0;

    // addVolatileAccessEvents the loops to the report builder
    for (loopAndRun <- runCountAndResultList) {
      val runResult = loopAndRun.runResult;
      val run = new util.LinkedList[RunElement]()

      for (eventForReport <- runResult) {
        run.add(
          new RunElement(new LoopRunAndThreadIndex(eventForReport.loopId, eventForReport.runId, eventForReport.threadIndex),
            eventForReport.runPosition, stacktraceLeaf(eventForReport.stacktraceNode, stacktraceLeafsMap), eventForReport.operationTextFactory,
            eventForReport.methodId))
      }

      val result = if (runResult.isFailure && runResult.dataRaceCount > 0) {
        failures = failures + 1;
        dataRaces = dataRaces + runResult.dataRaceCount;
        TestResult.FAILURE_AND_DATA_RACE
      } else if (runResult.isFailure) {
        failures = failures + 1;
        TestResult.FAILURE
      } else if (runResult.dataRaceCount > 0) {
        dataRaces = dataRaces + runResult.dataRaceCount;
        TestResult.DATA_RACE
      } else {
        TestResult.SUCCESS
      }

      reportBuilder.addLoopAndRun(new TestLoop(runResult.loopId, result,
        loopAndRun.count + 1), run);
    }

    // addVolatileAccessEvents the stacktrace leafs to the builder
    for (elem <- stacktraceLeafsMap) {
      reportBuilder.addStacktraceLeaf(elem._2)
    }

    val descriptionBuilder =  new DescriptionBuilderForReportImpl(reportBuilder);
    descriptionBuilder.setFailures(failures);
    descriptionBuilder.setDataRaces(dataRaces);
    descriptionBuilder
  }

  private def stacktraceLeaf(stacktraceNode: Option[StacktraceNode],
                             stacktraceLeafsMap: mutable.HashMap[StacktraceNode, StacktraceLeaf]): StacktraceLeaf = {
    stacktraceNode match {

      case Some(x) => {
        stacktraceLeafsMap.get(x) match {

          case Some(stacktraceLeaf) => stacktraceLeaf;

          case None => {
            val stacktraces = new util.LinkedList[StacktraceElement]()

            var current: Option[StacktraceNode] = Some(x);
            while (current.nonEmpty) {
              stacktraces.add(new StacktraceElement(current.get.methodId))
              current = current.get.getParent
            }

            val stacktraceLeaf = new StacktraceLeaf(stacktraces);
            stacktraceLeafsMap.put(stacktraceNode.get, stacktraceLeaf)
            stacktraceLeaf
          }
        }
      }

      case None => {
        new StacktraceLeaf(new util.LinkedList[StacktraceElement]());
      }
    }
  }

}
