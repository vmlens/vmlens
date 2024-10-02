package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.loopAndRunData.{RunData, RunResult}

/**
 * contains state, the map from loop id to loop state
 */

trait ProcessRun {
  def process(runData: RunData): RunResult;
}
