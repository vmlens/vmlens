package com.anarsoft.race.detection.report.run

import com.anarsoft.race.detection.report.ReportLoopData
import com.anarsoft.race.detection.report.builder.LinkAndFirstMethodName
import com.anarsoft.race.detection.report.description.DescriptionContext
import com.anarsoft.race.detection.report.element.{RunElement, StacktraceLeaf}
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap
import com.vmlens.report.createreport.CreateHtmlReport
import com.vmlens.report.stacktrace.UIStacktraceElement
import com.vmlens.report.summary.{UISummaryElement, UISummaryElementComparator}
import com.vmlens.report.trace.UIRunElement

import java.util
import java.util.LinkedList
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class CreateRunReport {

  /**
   *  consists of the following steps:
   * 1) setObjectHashCodeMap
   * 2) filter
   * 3) check size larger than threshold
   *  yes) check if data race
   *      yes) sort, and process data races
   *      no) create summary
   *  no) create trace
   * 
   * 4) create stack traces (if the stack trace was not already created)
   * 
   * either before or after filter we can create the tree
   *
   */
  
  def createReport(runData : ReportLoopData,
                   descriptionContext : DescriptionContext,
                   stacktraceToLink : mutable.HashMap[StacktraceLeaf,LinkAndFirstMethodName],
                   createHtmlReport : CreateHtmlReport): Unit = {
    val objectHashCodeMap = new ObjectHashCodeMap();

    for(element <- runData.runElements) {
      element.operationTextFactory.setObjectHashCodeMap(objectHashCodeMap,element.loopRunAndThreadIndex.threadIndex);
    }

    val sorted = runData.runElements.sorted

    val filtered = new ArrayBuffer[RunElement]()

    for(elem <- sorted ) {
      if(elem.operationTextFactory.take()) {
        filtered.append(elem)
      }
    }

    if(filtered.size > descriptionContext.reportAsSummaryThreshold(runData.loopId)) {
      if(runData.dataRaceCount > 0) {
        createDataRaceReport(filtered, descriptionContext, stacktraceToLink, createHtmlReport, runData);

      } else {
        createSummaryReport(filtered, descriptionContext, stacktraceToLink, createHtmlReport, runData);
      }
    }
    else {
      createTraceReport(filtered,descriptionContext,stacktraceToLink,createHtmlReport,runData);
    }

  }


  def createDataRaceReport(filtered : ArrayBuffer[RunElement],descriptionContext : DescriptionContext,
                           stacktraceToLink : mutable.HashMap[StacktraceLeaf,LinkAndFirstMethodName],
                           createHtmlReport : CreateHtmlReport,runData : ReportLoopData): Unit = {

    var position = 0;
    val startPositionList = new mutable.Queue[Int]()
    for (runElement <- filtered) {
      if(runElement.operationTextFactory.isDataRace) {
        startPositionList.enqueue( math.max( 0 , position - 5 ) )
      }
        position = position + 1
      }

    var startPosition : Option[Int] = Some(startPositionList.dequeue());
    position = 0;
    val uiRunElements = new util.LinkedList[UIRunElement]();
    for (runElement <- filtered) {

      startPosition match {

        case Some(x) => {
          if(x <= position && position < x + 10  ) {
            val linkAndFirstMethodName = createLinkAndFirstMethodName(runElement,
              stacktraceToLink,
              createHtmlReport,
              descriptionContext);
            val operation = runElement.operationTextFactory.operation
            val element = runElement.operationTextFactory.element(descriptionContext)
            val objectString = runElement.operationTextFactory.objectString(descriptionContext)
            val uiRunElement = new UIRunElement(runElement.runPosition, operation, element, objectString,
              linkAndFirstMethodName.firstMethodName, descriptionContext.threadName(runElement.loopRunAndThreadIndex),
              linkAndFirstMethodName.link, false)
            uiRunElements.add(uiRunElement);
          } else {
            if(! startPositionList.isEmpty) {
              startPosition   = Some(startPositionList.dequeue());
            } else {
              startPosition = None;
            }

          }
        }
        case None => {

        }

      }
      position = position + 1
    }
    createHtmlReport.createRunReport(uiRunElements, descriptionContext.loopName(runData.loopId), runData.runLink)

  }


  def createSummaryReport(filtered : ArrayBuffer[RunElement],descriptionContext : DescriptionContext,
                          stacktraceToLink : mutable.HashMap[StacktraceLeaf,LinkAndFirstMethodName],
                          createHtmlReport : CreateHtmlReport,runData : ReportLoopData): Unit = {

    val elementToCount = new mutable.HashMap[RunElement, Int]()
    for (runElement <- filtered) {
      elementToCount.get(runElement) match {

        case Some(x) => {
          elementToCount.put(runElement, x + 1)
        }

        case None => {
          elementToCount.put(runElement,1)
        }

      }
    }

    val uiRunElements = new util.LinkedList[UISummaryElement]();
    for(elem <- elementToCount) {
      val linkAndFirstMethodName = createLinkAndFirstMethodName(elem._1,
        stacktraceToLink,
        createHtmlReport,
        descriptionContext);

      val operation = elem._1.operationTextFactory.operation
      val element = elem._1.operationTextFactory.element(descriptionContext)
      val objectString = elem._1.operationTextFactory.objectString(descriptionContext)
      val uiRunElement = new UISummaryElement(operation, element, objectString,
        linkAndFirstMethodName.firstMethodName, descriptionContext.threadName(elem._1.loopRunAndThreadIndex),
        elem._2,
        linkAndFirstMethodName.link)
      uiRunElements.add(uiRunElement);
    }

    uiRunElements.sort(new UISummaryElementComparator());
    createHtmlReport.createSummaryReport(uiRunElements, descriptionContext.loopName(runData.loopId), runData.runLink)

  }



  def createTraceReport(filtered : ArrayBuffer[RunElement],descriptionContext : DescriptionContext,
                        stacktraceToLink : mutable.HashMap[StacktraceLeaf,LinkAndFirstMethodName],
                        createHtmlReport : CreateHtmlReport,runData : ReportLoopData): Unit = {
    val uiRunElements = new util.LinkedList[UIRunElement]();

    for (runElement <- filtered) {
      val linkAndFirstMethodName = createLinkAndFirstMethodName(runElement,
        stacktraceToLink,
        createHtmlReport,
        descriptionContext);
      val operation = runElement.operationTextFactory.operation
      val element = runElement.operationTextFactory.element(descriptionContext)
      val objectString = runElement.operationTextFactory.objectString(descriptionContext)
      val uiRunElement = new UIRunElement(runElement.runPosition, operation, element, objectString,
        linkAndFirstMethodName.firstMethodName, descriptionContext.threadName(runElement.loopRunAndThreadIndex),
        linkAndFirstMethodName.link, false)
      uiRunElements.add(uiRunElement);
    }

      createHtmlReport.createRunReport(uiRunElements, descriptionContext.loopName(runData.loopId), runData.runLink)
    }

  def createLinkAndFirstMethodName(runElement : RunElement,
                                   stacktraceToLink : mutable.HashMap[StacktraceLeaf,LinkAndFirstMethodName],
                                   createHtmlReport : CreateHtmlReport,
                                   descriptionContext : DescriptionContext) : LinkAndFirstMethodName = {
    if (runElement.stacktraceLeaf.stacktraceElements.isEmpty) {
      new LinkAndFirstMethodName(null, descriptionContext.methodName(runElement.inMethodId));
    }
    else {
      stacktraceToLink.get(runElement.stacktraceLeaf) match {
        case None => {
          val methodName = descriptionContext.methodName(runElement.stacktraceLeaf.stacktraceElements.head.methodId)
          if (runElement.stacktraceLeaf.stacktraceElements.size > 1) {
            val stacktraceElementList = new util.LinkedList[UIStacktraceElement]
            for (stacktraceElement <- runElement.stacktraceLeaf.stacktraceElements) {
              stacktraceElementList.add(new UIStacktraceElement(descriptionContext.methodName(stacktraceElement.methodId)))
            }
            val link = createHtmlReport.createStacktraceReport(stacktraceElementList);
            val newLink = new LinkAndFirstMethodName(link, methodName);
            stacktraceToLink.put(runElement.stacktraceLeaf, newLink)
            newLink
          } else {
            val methodName = descriptionContext.methodName(runElement.stacktraceLeaf.stacktraceElements.head.methodId)
            val newLink = new LinkAndFirstMethodName(null, methodName);
            stacktraceToLink.put(runElement.stacktraceLeaf, newLink)
            newLink
          }
        }
        case Some(x) => x;
      }
    }
  }
  
  

}
