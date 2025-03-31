package com.anarsoft.race.detection.reportbuilder


import com.anarsoft.race.detection.loopAndRunData.RunResult
import com.anarsoft.race.detection.process.main.{UILoopsAndStacktraceLeafsBuilder, LoopReportBuilder, RunCountAndResult}
import com.anarsoft.race.detection.reportbuilder.LoopReportBuilderImpl.toTestResult
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.report.builder.ReportBuilder
import com.vmlens.report.element.*
import com.vmlens.trace.agent.bootstrap.exception.Message

import java.util
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class LoopReportBuilderImpl(reportBuilder: ReportBuilder) extends LoopReportBuilder {

  private val runCountAndResultList = new ArrayBuffer[RunCountAndResult]();

  override def addRunResult(runResult: RunCountAndResult): Unit = {
    runCountAndResultList.append(runResult)
  }

  def build(): UILoopsAndStacktraceLeafsBuilder = {
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
            eventForReport.runPosition, stacktraceLeaf(eventForReport.stacktraceNode, stacktraceLeafsMap), eventForReport.runElementType,
            eventForReport.methodId))
      }

      if (runResult.isFailure) {
        failures = failures + 1;
      }
      dataRaces = dataRaces + runResult.dataRaceCount;
      
      
      reportBuilder.addLoopAndRun(new TestLoop(runResult.loopId, toTestResult(runResult),
        loopAndRun.count + 1), run);
    }

    // addVolatileAccessEvents the stacktrace leafs to the builder
    for (elem <- stacktraceLeafsMap) {
      reportBuilder.addStacktraceLeaf(elem._2)
    }

    val descriptionBuilder =  new UILoopsAndStacktraceLeafsBuilderImpl(reportBuilder);
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

object LoopReportBuilderImpl {

  def toTestResult(runResult : RunResult) : TestResult =  {
    val result : TestResultLabel = if (runResult.isFailure && runResult.dataRaceCount > 0) {
      TestResultLabel.FailureAndDataRace
    } else if (runResult.isFailure) {
      TestResultLabel.Failure
    } else if (runResult.dataRaceCount > 0) {
      TestResultLabel.DataRace
    } else {
      TestResultLabel.Success
    }

    var text = result.text;
    for(id <- runResult.warningIdList ) {
      text = text + ", " + Message.of(id).text();
    }
    
    new TestResult(text,result.style);
  }
  
}
