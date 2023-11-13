package com.anarsoft.race.detection.variable

import com.anarsoft.race.detection.variable.value.VariableValue

class ExclusiveVariable[TYPE] extends VariableForExtract[TYPE] {
  val value = new VariableValue[TYPE]();

  var nextId: Option[ExclusiveVariableId] = None;

  def requiredBy(id: ExclusiveVariableId): Unit = {
    value.requiredBy(id);
  }

  def put(in: TYPE, forId: ExclusiveVariableId): Unit = {
    nextId = Some(forId);
    value.put(in);
  }

  def take(id: VariableId): TYPE = {
    value.take(id);
  }

  def isAvailable(id: VariableId) = {
    if (value.isAvailable()) {
      nextId match {
        case Some(x) => x.equals(id);
        case None => false;
      }
    } else {
      false;
    }
  };
}
