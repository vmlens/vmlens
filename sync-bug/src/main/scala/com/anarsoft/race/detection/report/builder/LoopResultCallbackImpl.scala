package com.anarsoft.race.detection.report.builder

import com.anarsoft.race.detection.loopresult.LoopResult
import com.anarsoft.race.detection.report.ReportLoopData
import com.anarsoft.race.detection.report.description.{DescriptionCallbackImpl, NeedsDescriptionCallback}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.report.element.{LoopRunAndThreadIndex, RunElement, StacktraceElement, StacktraceLeaf, TestResult}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class LoopResultCallbackImpl extends LoopResultCallback {

  private val runCountAndResultList = new ArrayBuffer[LoopResult]();

  override def addRunResult(runResult: LoopResult): Unit = {
    runCountAndResultList.append(runResult)
  }

  def build(): Tuple2[DescriptionCallbackImpl,List[ReportLoopData]] = {
    val stacktraceLeafsMap = new mutable.HashMap[StacktraceNode, StacktraceLeaf]();
    val reportLoopDataList = new ArrayBuffer[ReportLoopData]();
    val descriptionBuilder = new DescriptionCallbackImpl();
    
    // addVolatileAccessEvents the loops to the report ordertree
    for (runResult <- runCountAndResultList) {
      val run = new  ArrayBuffer[RunElement]()
      for (eventForReport <- runResult) {
        val element =  new RunElement(new LoopRunAndThreadIndex(eventForReport.loopId, eventForReport.runId, eventForReport.threadIndex),
          eventForReport.runPosition, stacktraceLeaf(eventForReport.stacktraceNode, stacktraceLeafsMap,descriptionBuilder), eventForReport.runElementType,
          eventForReport.methodId);
        element.operationTextFactory.addToNeedsDescription(descriptionBuilder);
        descriptionBuilder.needsMethod(element.inMethodId)
        run.append(element)
      }

      for(warning <-  runResult.warningIdList) {
        warning.addToNeedsDescription(descriptionBuilder)
      }

      runResult.dominatorTree.foreach( dom => {
        val iter =  dom.graph.vertexSet.iterator();
        while(iter.hasNext){
          iter.next().addToNeedsDescription(descriptionBuilder)
        }
      } )


      reportLoopDataList.append(new ReportLoopData(runResult.loopId, 
        runResult.isFailure, 
        runResult.dataRaceCount, 
        runResult.warningIdList, 
        runResult.count + 1, 
        run.toList,
        runResult.dominatorTree));
      
    }
    
   Tuple2(descriptionBuilder, reportLoopDataList.toList);
  }

  private def stacktraceLeaf(stacktraceNode: Option[StacktraceNode],
                             stacktraceLeafsMap: mutable.HashMap[StacktraceNode, StacktraceLeaf],
                             needsDescriptionCallback : NeedsDescriptionCallback): StacktraceLeaf = {
    stacktraceNode match {

      case Some(x) => {
        stacktraceLeafsMap.get(x) match {

          case Some(stacktraceLeaf) => stacktraceLeaf;

          case None => {
            val stacktraces = new ArrayBuffer[StacktraceElement]()

            var current: Option[StacktraceNode] = Some(x);
            while (current.nonEmpty) {
              needsDescriptionCallback.needsMethod(current.get.methodId)
              stacktraces.append(new StacktraceElement(current.get.methodId))
              current = current.get.getParent
            }

            val stacktraceLeaf = new StacktraceLeaf(stacktraces.toList);
            stacktraceLeafsMap.put(stacktraceNode.get, stacktraceLeaf)
            stacktraceLeaf
          }
        }
      }

      case None => {
        new StacktraceLeaf(List());
      }
    }
  }

}
