package com.anarsoft.race.detection.sortutil

trait EventContainer[EVENT] {
  def getOrCreateElement(elem: EVENT): EventContainer[EVENT];

  def foreachOpposite(elem: EVENT, f: (EVENT) => Unit): Unit;
}
