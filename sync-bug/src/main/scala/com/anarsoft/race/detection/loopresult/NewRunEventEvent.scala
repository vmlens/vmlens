package com.anarsoft.race.detection.loopresult

import com.anarsoft.race.detection.report.EventForReportElement
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation
import com.anarsoft.race.detection.stacktrace.StacktraceNode

class NewRunEventEvent(val runId : Int) extends EventForReportElement {

  override def methodId: Int = 0;

  override def loopId: Int = 0;

  override def stacktraceNode: Option[StacktraceNode] =  None;

  override def runElementType: ReportOperation = new ReportOperationNoOP();

  override def threadIndex: Int = 0;

  override def runPosition: Int = 0;

  override def isNewRun : Boolean = true;
}
