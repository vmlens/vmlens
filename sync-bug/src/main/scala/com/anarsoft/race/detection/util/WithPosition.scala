package com.anarsoft.race.detection.util

import com.vmlens.report.assertion.Position

trait WithPosition {

  def threadIndex: Int;
  def runPosition: Int;

  def asPosition() : Position = {
    new Position(threadIndex, runPosition);
  }

}
