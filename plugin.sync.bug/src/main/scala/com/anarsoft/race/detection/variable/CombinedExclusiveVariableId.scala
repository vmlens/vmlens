package com.anarsoft.race.detection.variable

class CombinedExclusiveVariableId[TYPE](val elementToId:
                                        Map[CombinedExclusiveVariableElement[TYPE], ExclusiveVariableId]) {

  def getIdFor(elem: CombinedExclusiveVariableElement[TYPE]): ExclusiveVariableId = {
    elementToId.get(elem).get;
  }

}
