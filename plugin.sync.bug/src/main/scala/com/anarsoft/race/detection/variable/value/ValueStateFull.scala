package com.anarsoft.race.detection.variable.value

import com.anarsoft.race.detection.variable.VariableId

import scala.collection.mutable.Set

class ValueStateFull[TYPE](val open: Set[VariableId], val value: TYPE) extends ValueState[TYPE] {
  def requiredBy(id: VariableId): Unit = {
  }

  def take(id: VariableId): ValueAndState[TYPE] = {
    open -= id;
    if (open.isEmpty) {
      new ValueAndState[TYPE](value, ValueStateClosed[TYPE]());
    } else {
      new ValueAndState[TYPE](value, this);;
    }
  }

  def put(value: TYPE): ValueState[TYPE] = {
    new ValueStateFull(open, value);
  }

  def isAvailable(): Boolean = true;
}
