package com.anarsoft.race.detection.reportbuilder


import com.anarsoft.race.detection.rundata.RunResultImpl
import com.anarsoft.race.detection.loopresult.LoopResult
import com.anarsoft.race.detection.process.main.{LoopReportBuilder, UILoopsAndStacktraceLeafsBuilder}
import com.anarsoft.race.detection.reportbuilder.LoopReportBuilderImpl.toTestResult
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.report.ResultForVerify
import com.vmlens.report.builder.ReportBuilder
import com.vmlens.report.input.{LoopRunAndThreadIndex, RunElement, StacktraceElement, StacktraceLeaf, TestLoop, TestResult}

import java.util
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class LoopReportBuilderImpl(reportBuilder: ReportBuilder) extends LoopReportBuilder {

  private val runCountAndResultList = new ArrayBuffer[LoopResult]();

  override def addRunResult(runResult: LoopResult): Unit = {
    runCountAndResultList.append(runResult)
  }

  def build(): UILoopsAndStacktraceLeafsBuilder = {
    val stacktraceLeafsMap = new mutable.HashMap[StacktraceNode, StacktraceLeaf]();
    val  resultForVerify = new ResultForVerify();

    // addVolatileAccessEvents the loops to the report ordertree
    for (runResult <- runCountAndResultList) {
 
      val run = new util.LinkedList[RunElement]()

      for (eventForReport <- runResult) {
        run.add(
          new RunElement(new LoopRunAndThreadIndex(eventForReport.loopId, eventForReport.runId, eventForReport.threadIndex),
            eventForReport.runPosition, stacktraceLeaf(eventForReport.stacktraceNode, stacktraceLeafsMap), eventForReport.runElementType,
            eventForReport.methodId))
      }

      if (runResult.isFailure) {
        resultForVerify.setFailure(runResult.loopId)
      }
      if(runResult.dataRaceCount > 0) {
        resultForVerify.setDataRaces(runResult.loopId,runResult.dataRaceCount)
      }
      
      
      reportBuilder.addLoopAndRun(new TestLoop(runResult.loopId, toTestResult(runResult),
        runResult.count + 1), run);
    }

    // addVolatileAccessEvents the stacktrace leafs to the ordertree
    for (elem <- stacktraceLeafsMap) {
      reportBuilder.addStacktraceLeaf(elem._2)
    }

    val descriptionBuilder = new UILoopsAndStacktraceLeafsBuilderImpl(reportBuilder,resultForVerify);
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

  def toTestResult(runResult : LoopResult) : TestResult =  {
    val result : TestResultLabel = if (runResult.isFailure && runResult.dataRaceCount > 0) {
      TestResultLabel.FailureAndDataRace
    } else if (runResult.isFailure) {
      TestResultLabel.Failure
    } else if (runResult.dataRaceCount > 0) {
      TestResultLabel.DataRace
    } else {
      TestResultLabel.Success
    }
    val testResult = new TestResult(result.text);
    
    for(w  <- runResult.warningIdList) {
      w.addToTestResult(testResult);
    }
    
    testResult;
  }
  
}
