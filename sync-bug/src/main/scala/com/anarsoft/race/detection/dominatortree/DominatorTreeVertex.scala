package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}

/**
 * either VertexRoot , VertexMethod , VertexVolatileField
 * 
 * Multiple Volatile Field Events for the same hash code can be combined in one VolatileFieldVertex
 * 
 * We need to filter Volatile Field so that only fields which are accessed
 * multiple times are shown
 * 
 * We need also remove methods which do not contain a field access
 * 
 * 
 */
trait DominatorTreeVertex {
  
  def getLabel(descriptionContext: DescriptionContext) : String
  def addToNeedsDescription(needsDescriptionCallback : NeedsDescriptionCallback) : Unit;
  

}
