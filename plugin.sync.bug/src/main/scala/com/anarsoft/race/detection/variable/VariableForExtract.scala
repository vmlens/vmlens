package com.anarsoft.race.detection.variable

trait VariableForExtract[+TYPE] {
  def isAvailable(id: VariableId): Boolean;

  def take(id: VariableId): TYPE;
}
