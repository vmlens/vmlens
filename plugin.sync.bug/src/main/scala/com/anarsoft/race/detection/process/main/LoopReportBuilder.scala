package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.reportbuilder.DescriptionBuilder

trait LoopReportBuilder extends DescriptionBuilder {

  def build(): Unit;

}
