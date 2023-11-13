package com.anarsoft.race.detection.variable.value

import com.anarsoft.race.detection.variable.VariableId

trait ValueState[TYPE] {

  def requiredBy(id: VariableId): Unit;

  def take(id: VariableId): ValueAndState[TYPE];

  def put(value: TYPE): ValueState[TYPE];

  def isAvailable(): Boolean;

}
