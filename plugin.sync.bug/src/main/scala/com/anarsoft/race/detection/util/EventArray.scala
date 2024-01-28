package com.anarsoft.race.detection.util

import scala.math.Ordering.Implicits.*

class EventArray[+EVENT](private[this] val array: Array[EVENT]) {

  def iterator(): Iterator[EVENT] = new EventArrayIterator();

  private class EventArrayIterator extends Iterator[EVENT] {

    private var index = 0;

    override def hasNext: Boolean = {
      index < array.length;
    }

    override def next(): EVENT = {
      val temp = index;
      index = index + 1;
      array(temp);
    }
  }

  def sort[EVENT >: this.EVENT](ord: Ordering[EVENT]): Unit = {
    array.sortInPlace()(ord);
  }

  def foreach(f: (EVENT) => Unit): Unit = {
    for (elem <- array) {
      f(elem);
    }
  }

  override def equals(other: Any): Boolean = other match {
    case that: EventArray[EVENT] =>
      (that canEqual this) && (that sameArray this)
    case _ => false
  }

  private def canEqual(other: Any): Boolean = other.isInstanceOf[EventArray[EVENT]]

  private def arrayLength() = array.length;


  private def sameArray(other: EventArray[_]): Boolean = {
    if (other.arrayLength() != arrayLength()) {
      false;
    } else {
      val otherIter = other.iterator();
      val myIter = iterator();
      while (otherIter.hasNext) {
        if (myIter.next() != otherIter.next()) {
          return false;
        }
      }
      true;
    }
  }

  override def hashCode(): Int = {
    val state = Seq(array)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

}