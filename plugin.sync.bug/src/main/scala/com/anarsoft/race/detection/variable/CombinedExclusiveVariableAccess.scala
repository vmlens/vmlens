package com.anarsoft.race.detection.variable

class CombinedExclusiveVariableAccess[TYPE](val extractor: Extractor[TYPE], val variable: VariableForIntermediatePublisher,
                                            val id: ExclusiveVariableId) {

  def isAvailable(): Boolean = {
    extractor.isAvailable();
  }


  def take(): TYPE = {
    extractor.take();
  }

  def publish(): Unit = {
    variable.intermediatePublish(id);
  }

}
