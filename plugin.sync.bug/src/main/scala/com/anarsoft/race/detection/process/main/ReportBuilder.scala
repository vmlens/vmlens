package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.reportbuilder.RunReportBuilder

/**
 *
 * contains state, e.g. the already generated reports
 *
 */

trait ReportBuilder {

  def build(): Unit;

  def add(runReportBuilder: RunReportBuilder): Unit;

}
