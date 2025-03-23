package com.anarsoft.race.detection.createlastthreadposition

import com.anarsoft.race.detection.util.WithPosition

import scala.collection.mutable

class LastThreadPositionMap {

  private val threadIndexToPosition = new mutable.HashMap[Int,Int]();

  def put(withPosition : WithPosition): Unit = {
    threadIndexToPosition.get(withPosition.threadIndex) match  {
      case None => {
        threadIndexToPosition.put(withPosition.threadIndex,withPosition.runPosition)
      }
      case Some(x) => {
        if(x < withPosition.runPosition) {
          threadIndexToPosition.put(withPosition.threadIndex, withPosition.runPosition)
        }
      }
    }
  }

  def lastPosition(index : Int): Int = {
    threadIndexToPosition.get(index) match {
      // This case can happen for an empty thread
      // for this case the order is not necessary, so 0 is o.k.
      case  None => {
        0;
      }
      case Some(x) => {
        x
      }
    }
  }

}
