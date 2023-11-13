package com.anarsoft.race.detection.process

import com.anarsoft.race.detection.process.Transformation

class TransformationMethodMock(val transform: () => Boolean) extends Transformation {
  override def start(): Boolean = {
    transform();
  }
}
