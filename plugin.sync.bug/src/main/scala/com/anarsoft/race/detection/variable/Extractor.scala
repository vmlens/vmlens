package com.anarsoft.race.detection.variable

class Extractor[+TYPE](variableId: VariableId, val variable: VariableForExtract[TYPE]) {

  def isAvailable() = variable.isAvailable(variableId);

  def take() = variable.take(variableId);


}
