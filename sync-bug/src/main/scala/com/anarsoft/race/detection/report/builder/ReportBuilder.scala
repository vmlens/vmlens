package com.anarsoft.race.detection.report.builder

import com.anarsoft.race.detection.automatictest.IdToAutomaticTest
import com.anarsoft.race.detection.report.ReportLoopData
import com.anarsoft.race.detection.report.description.DescriptionContext
import com.anarsoft.race.detection.report.element.{StacktraceLeaf, TestResult}
import com.anarsoft.race.detection.report.run.{CreateDominatorTreeReport, CreateRunReport}
import com.vmlens.report.ResultForVerify
import com.vmlens.report.createreport.CreateHtmlReport
import com.vmlens.report.overview.{UITestLoop, UITestLoopAndWarning}

import java.nio.file.Path
import java.util
import scala.collection.mutable


class ReportBuilder(val reportLoopDataList : List[ReportLoopData],
                    val descriptionContext : DescriptionContext,
                    val idToAutomaticTest: IdToAutomaticTest,
                    val reportDir : Path)  {

  /**
   * create the overview report
   * delegates the processing of the run to CreateRunReport
   */
  def build(): ResultForVerify = {
    val resultForVerify = new ResultForVerify();
    val stacktraceToLink = new mutable.HashMap[StacktraceLeaf,LinkAndFirstMethodName]();
    val createHtmlReport = new CreateHtmlReport(reportDir);

    createHtmlReport.copyStaticFiled();

    val uiTestLoopAndWarnings = new util.LinkedList[UITestLoopAndWarning]()

    for(loop <- reportLoopDataList) {
      val loopName = descriptionContext.loopName(loop.loopId)
      val testResult = toTestResult(loop);
      val uiTestLoop = new UITestLoop(loopName,  loop.count, testResult.text )

      uiTestLoopAndWarnings.add(new  UITestLoopAndWarning(uiTestLoop,testResult.largeWarningList,loop));

      resultForVerify.setLoopName(loop.loopId,descriptionContext.loopName(loop.loopId))
      if(loop.isFailure){
        resultForVerify.setFailure(loop.loopId)
      }
      if (loop.dataRaceCount > 0) {
        resultForVerify.setDataRaces(loop.loopId,loop.dataRaceCount);
      }
    }
    
    createHtmlReport.createOverview(uiTestLoopAndWarnings);

    for (loop <- reportLoopDataList) {
      new CreateRunReport().createReport(loop,descriptionContext,stacktraceToLink,createHtmlReport)
      new CreateDominatorTreeReport().createReport(loop, descriptionContext, createHtmlReport)
    }

    idToAutomaticTest.buildPreAnalyzedClasses(descriptionContext.idToAutomaticTestClassName.toMap,descriptionContext,reportDir)
    
    resultForVerify;
  }

  def toTestResult(runResult: ReportLoopData): TestResult = {
    val result: TestResultLabel = if (runResult.isFailure && runResult.dataRaceCount > 0) {
      TestResultLabel.FailureAndDataRace
    } else if (runResult.isFailure) {
      TestResultLabel.Failure
    } else if (runResult.dataRaceCount > 0) {
      TestResultLabel.DataRace
    } else {
      TestResultLabel.Success
    }
    val testResult = new TestResult(result.text);

    for (w <- runResult.warningIdList) {
      w.addToTestResult(descriptionContext,testResult);
    }

    testResult;
  }
  
}