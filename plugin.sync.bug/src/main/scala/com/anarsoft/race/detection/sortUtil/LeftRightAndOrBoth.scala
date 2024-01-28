package com.anarsoft.race.detection.sortUtil

class LeftRightAndOrBoth[EVENT <: SortableEvent]
(private var left: Option[EVENT],
 private var right: Option[EVENT],
 private var both: Option[EVENT]) {


  def create(elem: EVENT): LeftRightAndOrBoth[EVENT] = {
    if (elem.isLeftOnly()) {
      new LeftRightAndOrBoth(Option(elem), right, both);
    } else if (elem.isRightOnly()) {
      new LeftRightAndOrBoth(left, Option(elem), both);
    } else {
      new LeftRightAndOrBoth(left, right, Option(elem));
    }
  }

  def foreachOpposite(elem: EVENT, f: (EVENT) => Unit): Unit = {
    if (elem.isLeftOnly()) {
      right.foreach(f);
      both.foreach(f);
    } else if (elem.isRightOnly()) {
      left.foreach(f);
      both.foreach(f);
    } else {
      left.foreach(f);
      right.foreach(f);
      both.foreach(f);
    }
  }

}

object LeftRightAndOrBoth {

  def apply[EVENT <: SortableEvent](elem: EVENT): LeftRightAndOrBoth[EVENT] = {
    if (elem.isLeftOnly()) {
      new LeftRightAndOrBoth[EVENT](Option(elem), Option.empty, Option.empty);
    } else if (elem.isRightOnly()) {
      new LeftRightAndOrBoth[EVENT](Option.empty, Option(elem), Option.empty);
    } else {
      new LeftRightAndOrBoth[EVENT](Option.empty, Option.empty, Option(elem));
    }
  }

}