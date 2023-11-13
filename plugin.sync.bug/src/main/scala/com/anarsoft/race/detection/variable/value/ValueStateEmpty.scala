package com.anarsoft.race.detection.variable.value

import com.anarsoft.race.detection.variable.VariableId

import scala.collection.mutable.Set

class ValueStateEmpty[TYPE] extends ValueState[TYPE] {

  val required = Set[VariableId]();

  def requiredBy(id: VariableId): Unit = {
    required += id;
  }

  def take(id: VariableId): ValueAndState[TYPE] = {
    null
  }

  def put(value: TYPE): ValueState[TYPE] = {
    new ValueStateFull[TYPE](required, value);
  }

  def isAvailable(): Boolean = false;
}
