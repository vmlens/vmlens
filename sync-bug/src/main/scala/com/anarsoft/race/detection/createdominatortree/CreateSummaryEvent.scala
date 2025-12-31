package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.createdominatortreeevent.{BuildDominatorTreeContext, EventForSummary, EventForSummaryOrdering, SummaryEvent}
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.GenericMemoryAccessKey
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap
import com.anarsoft.race.detection.util.EventArray

class CreateSummaryEvent[MEMORY_ACCESS_KEY <: GenericMemoryAccessKey[MEMORY_ACCESS_KEY]] {

  /**
   * sort by threadIndex, dominatorTreeCounter, memoryAccessKey
   * option current summary event -> add to set of memory access
   *
   * @param context
   */
  def process(eventArray: EventArray[EventForSummary[MEMORY_ACCESS_KEY]],
              context: BuildDominatorTreeContext): Unit = {

    val objectHashCodeMap = new ObjectHashCodeMap();
   // set the ObjectHashCodeMap
    for (event <- eventArray) {
      event.setObjectHashCodeMap(objectHashCodeMap)
    }
    
    eventArray.sort(new EventForSummaryOrdering[MEMORY_ACCESS_KEY]);
    var current: Option[SummaryEvent] = None;
    for (event <- eventArray) {
      event.createUIStateElementSortKey() match {
        case None => {
          // filtered
        }
        case Some(sortKey) => {
          current match {
            case Some(summary) => {
              if (summary.threadIndex == event.threadIndex &&
                summary.dominatorTreeCounter == event.dominatorTreeCounter &&
                summary.memoryAccessKey == event.memoryAccessKey) {
                summary.operationSet.add(event.operation);
              } else {
                context.eventList.append(summary)
                current = Some(SummaryEvent(event,sortKey));
              }

            }
            case None => {
              current = Some(SummaryEvent(event,sortKey));
            }
          }
        }
      }
    }
    current match {
      case Some(summary) => {
        context.eventList.append(summary)
      }
      case None => {

      }

    }
  }
}
