package com.anarsoft.race.detection.groupnonvolatile

import com.anarsoft.race.detection.reportbuilder.StaticMemoryAccessId
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.SortedMemoryAccessList

import scala.collection.mutable

class GroupNonVolatileMemoryAccessElementForResult(val dataRaces: Set[StaticMemoryAccessId],
                                                   val sortedLists: List[SortedMemoryAccessList]) {

}

object GroupNonVolatileMemoryAccessElementForResult {

  def apply(sortedLists: List[SortedMemoryAccessList]): GroupNonVolatileMemoryAccessElementForResult = {
    val dataRaces = new mutable.HashSet[StaticMemoryAccessId]()
    for (elem <- sortedLists) {
      dataRaces.addAll(elem.dataRaces);
    }
    new GroupNonVolatileMemoryAccessElementForResult(dataRaces.toSet, sortedLists);
  }
}
