package com.anarsoft.race.detection.createdominatortreeevent

import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap
import com.vmlens.report.dominatortree.UIStateElementSortKey

/**
 * either a volatile field access or AtomicNonBlockingEvent
 */
trait EventForSummary[MEMORY_ACCESS_KEY]  {

    def threadIndex: Int;
    def dominatorTreeCounter: Int;
    def operation : Int;
    
    def memoryAccessKey : MEMORY_ACCESS_KEY;
    
    def setObjectHashCodeMap(objectHashCodeMap : ObjectHashCodeMap) : Unit;
    def createUIStateElementSortKey() : Option[UIStateElementSortKey];
    
}
