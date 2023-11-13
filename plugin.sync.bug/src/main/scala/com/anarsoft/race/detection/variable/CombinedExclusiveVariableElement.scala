package com.anarsoft.race.detection.variable

trait CombinedExclusiveVariableElement[+TYPE] extends VariableForIntermediatePublisher {
  def createExtractor(id: ExclusiveVariableId): Extractor[TYPE];
}
