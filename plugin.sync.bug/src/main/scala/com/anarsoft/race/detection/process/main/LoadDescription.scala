package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.process.description.DescriptionData

trait LoadDescription {
  def load(): DescriptionData;
}
