package com.anarsoft.race.detection.variable

class ExclusivePublisher[TYPE](val variable: ExclusiveVariable[TYPE], val id: ExclusiveVariableId) {

  def publish(value: TYPE): Unit = {
    variable.put(value, id);
  }

}
