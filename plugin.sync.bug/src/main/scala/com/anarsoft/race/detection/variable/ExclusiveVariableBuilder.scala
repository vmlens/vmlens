package com.anarsoft.race.detection.variable

class ExclusiveVariableBuilder[TYPE] extends CombinedExclusiveVariableElement[TYPE] {
  val variable = new ExclusiveVariable[TYPE]

  def createExtractor(id: ExclusiveVariableId): Extractor[TYPE] = {
    variable.requiredBy(id);
    new Extractor[TYPE](id, variable);
  }

  def createPublisher(id: ExclusiveVariableId): ExclusivePublisher[TYPE] = {
    new ExclusivePublisher[TYPE](variable, id);
  }

  def newId(): ExclusiveVariableId = {
    new ExclusiveVariableId();
  }
}
