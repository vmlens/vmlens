package com.anarsoft.race.detection.nonvolatilememoryaccessgroup

import com.anarsoft.race.detection.reportbuilder.StaticMemoryAccessId
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.SortedMemoryAccessList

import scala.collection.mutable

class NonVolatileMemoryAccessElementForResult(val dataRaces: Set[StaticMemoryAccessId],
                                              val sortedLists: List[SortedMemoryAccessList]) {

}

object NonVolatileMemoryAccessElementForResult {

  def apply(sortedLists: List[SortedMemoryAccessList]): NonVolatileMemoryAccessElementForResult = {
    val dataRaces = new mutable.HashSet[StaticMemoryAccessId]()
    for (elem <- sortedLists) {
      dataRaces.addAll(elem.dataRaces);
    }
    new NonVolatileMemoryAccessElementForResult(dataRaces.toSet, sortedLists);
  }
}
