package com.anarsoft.race.detection.util

import scala.math.Ordering.Implicits.*

class EventArray[+EVENT <: ThreadIdAndMethodCounter](private[this] val array: Array[EVENT]) {

  def iterator(): Iterator[EVENT] = new EventArrayIterator();

  class EventArrayIterator extends Iterator[EVENT] {

    var index = 0;

    override def hasNext: Boolean = {
      index < array.length;
    }

    override def next(): EVENT = {
      val temp = index;
      index = index + 1;
      array(temp);
    }
  }


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