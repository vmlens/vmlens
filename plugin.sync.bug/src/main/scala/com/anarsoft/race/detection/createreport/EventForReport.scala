package com.anarsoft.race.detection.createreport

import com.anarsoft.race.detection.reportbuilder.RunReportBuilder

// visitor pattern, the report builder has a method for each event class
trait EventForReport {
  def addToRunReportBuilder(runReportBuilder: RunReportBuilder): Unit;
}
