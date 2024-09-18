package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.process.description.DescriptionData
import com.anarsoft.race.detection.reportbuilder.DescriptionBuilder

trait ProcessDescription {
  def process(descriptionData: DescriptionData): DescriptionBuilder;
}
