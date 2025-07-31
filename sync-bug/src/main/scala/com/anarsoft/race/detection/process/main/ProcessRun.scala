package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.loopAndRunData.{RunData, RunResult}
import com.anarsoft.race.detection.process.run.ProcessRunContext

/**
 * contains state, the map from loop id to loop state
 */

trait ProcessRun {
  def process(runData: RunData): RunResult;
  def runContext : ProcessRunContext;
}
