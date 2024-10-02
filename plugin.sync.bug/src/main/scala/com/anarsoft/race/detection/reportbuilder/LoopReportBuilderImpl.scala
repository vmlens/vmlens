package com.anarsoft.race.detection.reportbuilder


import com.anarsoft.race.detection.process.main.{LoopReportBuilder, RunCountAndResult}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.trace.agent.description.{ClassDescription, ThreadOrLoopDescription}
import com.vmlens.report.builder.ReportBuilder
import com.vmlens.report.element.*
import com.vmlens.report.uielement.UILoopsAndStacktraceLeafs

import java.util
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class LoopReportBuilderImpl(reportBuilder: ReportBuilder) extends LoopReportBuilder {

  val stacktraceLeafsMap = new mutable.HashMap[StacktraceNode, Option[StacktraceLeaf]]();
  val runCountAndResultList = new ArrayBuffer[RunCountAndResult]();

  override def addRunResult(runResult: RunCountAndResult): Unit = {
    // collect all stacktrace leafs in a set
    for (eventForReport <- runResult.runResult) {
      eventForReport.stacktraceNode.foreach(node => stacktraceLeafsMap.put(node, None))
    }
    runCountAndResultList.append(runResult)
  }

  override def addClassDescription(classDescription: ClassDescription): Unit = {

  }

  override def addThreadOrLoopDescription(threadOrLoopDescription: ThreadOrLoopDescription): Unit = {

  }

  def build(): UILoopsAndStacktraceLeafs = {
    /*
     // and add the elemnts to the run
      RunElement(LoopRunAndThreadIndex loopRunAndThreadIndex, int runPosition, StacktraceLeaf stacktraceLeaf,
        OperationTextFactory operationTextFactory)

     */

    // add the loops to the report builder
    for (loopAndRun <- runCountAndResultList) {

      val runResult = loopAndRun.runResult;

      val run = new util.LinkedList[RunElement]()

      for (eventForReport <- runResult) {
        run.add(
          new RunElement(new LoopRunAndThreadIndex(eventForReport.loopId, eventForReport.runId, eventForReport.threadIndex),
            eventForReport.runPosition, stacktraceLeaf(eventForReport.stacktraceNode), eventForReport.operationTextFactory))
      }


      val textAndStyle = if (runResult.isFailure && runResult.dataRaceCount > 0) {
        Tuple2("Failure and data race", "style=\"color: red;\"")
      } else if (runResult.isFailure) {
        Tuple2("Failure", "style=\"color: red;\"")
      } else if (runResult.dataRaceCount > 0) {
        Tuple2("Data race", "style=\"color: red;\"")
      } else {
        Tuple2("Success", "")
      }

      reportBuilder.addLoopAndRun(new TestLoop(runResult.loopAndRunId.loopId, textAndStyle._1, textAndStyle._2,
        loopAndRun.count + 1), run);
    }

    // add the stacktrace leafs to the builder

    reportBuilder.build();
  }

  private def stacktraceLeaf(stacktraceNode: Option[StacktraceNode]): StacktraceLeaf = {
    new StacktraceLeaf(-1, new util.LinkedList[StacktraceElement]());
  }

}
