package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.reportbuilder.RunReportBuilderAdapter

/**
 *
 * contains state, e.g. the already generated reports
 *
 */

trait ReportBuilder {

  def build(): Unit;

  def add(runReportBuilder: RunReportBuilderAdapter): Unit;

}
