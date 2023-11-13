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
              function: CombinedExclusiveVariableAccess[TYPE] => Unit): Unit = {
    for (elem <- sourceId.elementToId) {
      val targetVariableId = targetId.getIdFor(elem._1);
      val access = new CombinedExclusiveVariableAccess[TYPE](elem._1.createExtractor(elem._2), elem._1, targetVariableId);
      function(access);

    }
  }

  def foreach(sourceId: CombinedExclusiveVariableId[TYPE],
              function: Extractor[TYPE] => Unit): Unit = {
    for (elem <- sourceId.elementToId) {
      function(elem._1.createExtractor(elem._2));
    }
  }


}