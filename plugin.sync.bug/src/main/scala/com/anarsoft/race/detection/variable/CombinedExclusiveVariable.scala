package com.anarsoft.race.detection.variable

import scala.collection.mutable.Map

class CombinedExclusiveVariable[TYPE](val elements: Seq[CombinedExclusiveVariableElement[TYPE]]) {

  def newId(): CombinedExclusiveVariableId[TYPE] = {
    val map = Map[CombinedExclusiveVariableElement[TYPE], ExclusiveVariableId]();

    for (elem <- elements) {
      map.put(elem, new ExclusiveVariableId());
    }

    new CombinedExclusiveVariableId[TYPE](map.toMap);
  }

  def foreach(sourceId: CombinedExclusiveVariableId[TYPE], targetId: CombinedExclusiveVariableId[TYPE],
              access: CombinedExclusiveVariableAccess[TYPE] => Unit): Unit = {

  }

  def foreach(sourceId: CombinedExclusiveVariableId[TYPE],
              extract: CombinedExclusiveVariableExtractor[TYPE] => Unit): Unit = {

  }


}