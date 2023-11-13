package com.anarsoft.race.detection.variable.value

import com.anarsoft.race.detection.variable.VariableId

class VariableValue[TYPE] {

  var state: ValueState[TYPE] = new ValueStateEmpty[TYPE]();

  def requiredBy(id: VariableId): Unit = state.requiredBy(id);


  def take(id: VariableId): TYPE = {
    val result = state.take(id);
    state = result.state;
    result.value;
  }

  def put(value: TYPE): Unit = {
    state = state.put(value);
  }

  def isAvailable(): Boolean = state.isAvailable();

}

