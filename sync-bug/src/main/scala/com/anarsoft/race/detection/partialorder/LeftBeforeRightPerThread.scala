package com.anarsoft.race.detection.partialorder


import java.util.TreeMap
import scala.math.max

class LeftBeforeRightPerThread {

  private val treeMap = TreeMap[Integer, Integer]();

  def maxPositionForKeyBefore(right: Int): Option[Int] = {
    val iter = treeMap.headMap(right, true).values().iterator();
    var maxLeft = -1;
    while (iter.hasNext) {
      val value = iter.next();
      maxLeft = max(maxLeft, value);
    }
    if (maxLeft > -1) {
      Option(maxLeft);
    } else {
      Option.empty;
    }
  }

  def addLeftBeforeRight(left: Int, right: Int): Unit = {
    val current = treeMap.get(right);
    if (current == null) {
      treeMap.put(right, left)
    } else {
      treeMap.put(right, max(left, current.intValue()))
    }

  }
}
