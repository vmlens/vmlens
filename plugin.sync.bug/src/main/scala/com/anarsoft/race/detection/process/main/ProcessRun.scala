package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.process.loopAndRunData.RunData
import com.anarsoft.race.detection.reportbuilder.RunReportBuilder

/**
 * contains state, the map from loop id to loop state
 */

trait ProcessRun {
  def process(runData: RunData, runReportBuilder: RunReportBuilder): Unit;
}
