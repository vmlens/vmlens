package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.report.description.DescriptionCallback


trait LoadDescription {

  def load(descriptionBuilder: DescriptionCallback): Unit;
  
}
