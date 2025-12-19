package com.anarsoft.race.detection.report.run

import com.anarsoft.race.detection.report.ReportLoopData
import com.anarsoft.race.detection.report.builder.LinkAndFirstMethodName
import com.anarsoft.race.detection.report.description.DescriptionContext
import com.anarsoft.race.detection.report.element.{RunElement, StacktraceLeaf}
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap
import com.vmlens.report.createreport.CreateHtmlReport
import com.vmlens.report.stacktrace.UIStacktraceElement
import com.vmlens.report.trace.UIRunElement

import java.util
import java.util.LinkedList
import scala.collection.mutable

class CreateRunReport {

  /*
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
    val uiRunElements = new util.LinkedList[UIRunElement]();

    for (runElement <- sorted) {
      val linkAndFirstMethodName = createLinkAndFirstMethodName(runElement,
        stacktraceToLink,
        createHtmlReport,
        descriptionContext);


      val operation = runElement.operationTextFactory.operation
      val element = runElement.operationTextFactory.element(descriptionContext)
      val objectString = runElement.operationTextFactory.objectString(descriptionContext)
      val uiRunElement = new UIRunElement(runElement.runPosition, operation, element, objectString ,
        linkAndFirstMethodName.firstMethodName, descriptionContext.threadName(runElement.loopRunAndThreadIndex),
        linkAndFirstMethodName.link, false)
      uiRunElements.add(uiRunElement);


    }

    createHtmlReport.createRunReport(uiRunElements,descriptionContext.loopName(runData.loopId) , runData.runLink)

  }

  def createLinkAndFirstMethodName(runElement : RunElement,
                                   stacktraceToLink : mutable.HashMap[StacktraceLeaf,LinkAndFirstMethodName],
                                   createHtmlReport : CreateHtmlReport,
                                   descriptionContext : DescriptionContext) : LinkAndFirstMethodName = {
    if(runElement.stacktraceLeaf.stacktraceElements.isEmpty) {
      new LinkAndFirstMethodName(null,descriptionContext.methodName(runElement.inMethodId));
    }
    else {
      stacktraceToLink.get(runElement.stacktraceLeaf) match {
        case None => {
          val methodName = descriptionContext.methodName(runElement.stacktraceLeaf.stacktraceElements.head.methodId)
          if(runElement.stacktraceLeaf.stacktraceElements.size > 1) {
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
