package com.anarsoft.race.detection.variable

class SharedVariableBuilder[TYPE]() {

  val variable = new SharedVariable[TYPE]

  def createExtractor(): Extractor[TYPE] = {
    val id = new VariableId();
    variable.requiredBy(id);
    new Extractor[TYPE](id, variable);
  }

  def createPublisher(): SharedPublisher[TYPE] = {
    new SharedPublisher[TYPE](variable);
  }
}


