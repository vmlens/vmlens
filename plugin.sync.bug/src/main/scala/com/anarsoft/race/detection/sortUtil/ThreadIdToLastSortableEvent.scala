package com.anarsoft.race.detection.sortUtil

import com.anarsoft.race.detection.util.WithPosition

import scala.collection.mutable.HashMap

class ThreadIdToLastSortableEvent[EVENT <: WithPosition](newContainer: (EVENT) => EventContainer[EVENT]) {

  private val map = new HashMap[Long, EventContainer[EVENT]]();

  def foreachOppositeAndPut(elem: EVENT, f: (EVENT) => Unit): Unit = {
    foreachOpposite(elem, f);
    put(elem);
  }

  private def foreachOpposite(elem: EVENT, f: (EVENT) => Unit): Unit = {
    map.foreach((tuple) => {
      if (tuple._1 != elem.threadId) {
        tuple._2.foreachOpposite(elem, f);
      }
    }
    );
  }

  private def put(elem: EVENT): Unit = {
    val newElement =
      map.get(elem.threadId) match {
        case None => {
          newContainer(elem);
        }
        case Some(x) => {
          x.create(elem);
        }
      }
    map.put(elem.threadId, newElement);
  }

}
