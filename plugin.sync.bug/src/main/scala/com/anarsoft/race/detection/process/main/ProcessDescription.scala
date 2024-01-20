package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.process.description.DescriptionData
import com.anarsoft.race.detection.report.DescriptionBuilder

trait ProcessDescription {
  def process(descriptionData: DescriptionData): DescriptionBuilder;
}
