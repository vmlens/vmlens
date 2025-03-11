package com.anarsoft.race.detection.processeventbytype

import com.anarsoft.race.detection.util.EventArray

class ProcessEventByType[EVENT <: WithCompareType[EVENT]](val lifecycle: AlgorithmForOneTypeFactory[EVENT]) {

  def process(array: EventArray[EVENT]) = {
    val iter = array.iterator();
    var currentOption: Option[AlgorithmForOneTypeAndEvent[EVENT]] = None;
    while (iter.hasNext) {
      val event = iter.next();
      var current = {
        currentOption match {
          case None => {
            val x = AlgorithmForOneTypeAndEvent(lifecycle.create(), event);
            currentOption = Some(x);
            x
          }
          case Some(x) => {
            x
          }
        }
      }
      if (current.event.compareType(event) != 0) {
        currentOption.foreach(x => x.algorithm.stop())
        current = AlgorithmForOneTypeAndEvent(lifecycle.create(), event);
        currentOption = Some(current);
      }
      current.algorithm.prozess(event);
    }
    currentOption.foreach(x => x.algorithm.stop())
  }
}
