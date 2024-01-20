package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.process.loopAndRunData.RunData
import com.anarsoft.race.detection.report.RunReportBuilder

/**
 * contains state, the map from loop id to loop state
 */

trait ProcessRun {
  def process(runData: RunData): RunReportBuilder;
}
