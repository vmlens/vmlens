package com.anarsoft.race.detection.createdominatortreeevent

import com.anarsoft.race.detection.createdominatortree.DominatorMemoryAccessKeyToOperation
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
    
    def setMapsForDominatorTree(objectHashCodeMap : ObjectHashCodeMap, 
                                objectHashCodeToOperation : DominatorMemoryAccessKeyToOperation) : Unit;
    def createUIStateElementSortKey() : Option[UIStateElementSortKey];
    
    def isReadOnly : Boolean;
    
}
