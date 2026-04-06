package com.anarsoft.race.detection.dominatortree.key

import com.anarsoft.race.detection.dominatortree.VertexState
import com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.DominatorMemoryAccessKey
import com.vmlens.report.dominatortree.UIStateElementSortKey

case class StateVertexKey(memoryAccessKey: DominatorMemoryAccessKey, objectHashCode : UIStateElementSortKey) {

  def create() : VertexState =  new VertexState(this);
  
  
}
