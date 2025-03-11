package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.reportbuilder.DescriptionBuilder


trait LoadDescription {

  def load(descriptionBuilder: DescriptionBuilder): Unit;
  
}
