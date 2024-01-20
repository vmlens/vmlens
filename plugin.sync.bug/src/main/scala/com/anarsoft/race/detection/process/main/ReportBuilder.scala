package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.report.RunReportBuilder

/**
 *
 * contains state, e.g. the already generated reports
 *
 */

trait ReportBuilder {

  def build(): Unit;

  def add(runReportBuilder: RunReportBuilder): Unit;

}
