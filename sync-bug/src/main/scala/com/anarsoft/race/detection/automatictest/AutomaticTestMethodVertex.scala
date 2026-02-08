package com.anarsoft.race.detection.automatictest

import com.anarsoft.race.detection.report.description.NeedsDescriptionCallback

case class AutomaticTestMethodVertex(methodId : Int) {

  def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    callback.needsMethod(methodId)
  }

}
