package com.anarsoft.race.detection.util

import scala.math.Ordering.Implicits.*

class EventArray[+EVENT <: ThreadIdAndMethodCounter](private[this] val array: Array[EVENT]) {

  def foreach(f: (EVENT) => Unit): Unit = {
    for (elem <- array) {
      f(elem);
    }
  }

  def sortByMethodCounter(): Unit = {
    array.sortInPlaceWith((a, b) =>
      if (a.threadId != b.threadId) a.threadId < b.threadId
      else a.methodCounter < b.methodCounter
    )
  }

}