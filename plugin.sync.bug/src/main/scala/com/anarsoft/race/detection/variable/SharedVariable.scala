package com.anarsoft.race.detection.variable

import com.anarsoft.race.detection.variable.value.VariableValue

class SharedVariable[TYPE] extends VariableForExtract[TYPE] {

  val value = new VariableValue[TYPE]();

  def requiredBy(id: SharedVariableId): Unit = {
    value.requiredBy(id);
  }

  def put(in: TYPE): Unit = {
    value.put(in);
  }

  def take(id: VariableId): TYPE = {
    value.take(id);
  }

  def isAvailable(id: VariableId) = value.isAvailable();

}
