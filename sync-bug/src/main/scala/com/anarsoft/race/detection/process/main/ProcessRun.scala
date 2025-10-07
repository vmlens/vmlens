package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.rundata.{RunData, RunResultImpl}
import com.anarsoft.race.detection.process.run.ProcessRunContext

/**
 * contains state, the map from loop id to loop state
 */

trait ProcessRun {
  def process(runData: RunData): RunResultImpl;
  def runContext : ProcessRunContext;
}
