package com.anarsoft.race.detection.variable

class ExclusiveVariableBuilder[TYPE] {
  val variable = new ExclusiveVariable[TYPE]

  def createExtractor(id: VariableId): Extractor[TYPE] = {
    variable.requiredBy(id);
    new Extractor[TYPE](id, variable);
  }

  def createPublisher(id: VariableId): ExclusivePublisher[TYPE] = {
    new ExclusivePublisher[TYPE](variable, id);
  }
}
