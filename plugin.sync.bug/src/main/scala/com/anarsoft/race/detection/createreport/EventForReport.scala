package com.anarsoft.race.detection.createreport

import com.anarsoft.race.detection.reportbuilder.RunReportBuilderAdapter

// visitor pattern, the report builder has a method for each event class
trait EventForReport {
  def addToRunReportBuilder(runReportBuilder: RunReportBuilderAdapter): Unit;
}
