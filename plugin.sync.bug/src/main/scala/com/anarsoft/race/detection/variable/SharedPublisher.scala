package com.anarsoft.race.detection.variable

class SharedPublisher[TYPE](val variable: SharedVariable[TYPE]) {

  def publish(value: TYPE): Unit = {
    variable.put(value);
  }

}
