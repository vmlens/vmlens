package com.anarsoft.race.detection.variable.value

import com.anarsoft.race.detection.variable.VariableId

import scala.collection.mutable.Set

class ValueStateClosed[TYPE]() extends ValueState[TYPE] {
  def requiredBy(id: VariableId): Unit = {
  }

  def take(id: VariableId): ValueAndState[TYPE] = {
    null;
  }

  def put(value: TYPE): ValueState[TYPE] = {
    this;
  }

  def isAvailable(): Boolean = false;


}
