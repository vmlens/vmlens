package com.anarsoft.race.detection.sortutil

import com.anarsoft.race.detection.util.WithPosition

import scala.collection.mutable.HashMap

class ThreadIdToLastSortableEvent[EVENT <: WithPosition](newContainer: (EVENT) => EventContainer[EVENT]) {

  private val map = new HashMap[Long, EventContainer[EVENT]]();

  def foreachOppositeAndPut(elem: EVENT, f: (EVENT) => Unit): Unit = {
    foreachOpposite(elem, f);
    put(elem);
  }


  def foreachOpposite(elem: EVENT, f: (EVENT) => Unit): Unit = {
    map.foreach((tuple) => {
      if (tuple._1 != elem.threadIndex) {
        tuple._2.foreachOpposite(elem, f);
      }
    }
    );
  }

  def put(elem: EVENT): Unit = {
    val newElement =
      map.get(elem.threadIndex) match {
        case None => {
          newContainer(elem);
        }
        case Some(x) => {
          x.put(elem);
        }
      }
    map.put(elem.threadIndex, newElement);
  }

}
