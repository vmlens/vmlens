package com.anarsoft.race.detection.variable.value

case class ValueAndState[TYPE](val value: TYPE, val state: ValueState[TYPE]) {

}
