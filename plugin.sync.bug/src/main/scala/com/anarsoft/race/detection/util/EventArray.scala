package com.anarsoft.race.detection.util

import java.util
import scala.math.Ordering.Implicits.*
import scala.reflect.ClassTag

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
    case that: EventArray[_] =>
      (that canEqual this) && (that sameArray this)
    case _ => false
  }

  private def canEqual(other: Any): Boolean = other.isInstanceOf[EventArray[_]]

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

object EventArray {

  def apply[EVENT: ClassTag](list: util.List[EVENT]): EventArray[EVENT] = {
    val array = Array.ofDim[EVENT](list.size());
    var index = 0;
    val iter = list.iterator();
    while (iter.hasNext) {
      array(index) = iter.next();
      index = index + 1;
    }
    new EventArray[EVENT](array);
  }

}