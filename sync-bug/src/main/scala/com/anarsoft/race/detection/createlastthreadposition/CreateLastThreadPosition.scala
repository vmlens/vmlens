package com.anarsoft.race.detection.createlastthreadposition

import com.anarsoft.race.detection.util.{EventArray, WithPosition}

class CreateLastThreadPosition {

  def process(eventArray: EventArray[WithPosition],
              lastThreadPositionMap : LastThreadPositionMap): Unit = {
    eventArray.sort(new PositionOrdering());
    var previous : Option[WithPosition] = None;
   
    for(event <- eventArray) {
      previous match {
        case None => {
          previous = Some(event);  
        }
        case Some(x) =>{
          if(x.threadIndex != event.threadIndex) {
            lastThreadPositionMap.put(x)
          }
          previous = Some(event);
        }
      }
    }

    previous match {
      case None => {
      }
      case Some(x) => {
          lastThreadPositionMap.put(x)
      }
    }
    
  }
}
