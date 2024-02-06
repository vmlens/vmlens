package com.anarsoft.race.detection.sortUtil

trait EventContainer[EVENT] {
  def getOrCreateElement(elem: EVENT): EventContainer[EVENT];

  def foreachOpposite(elem: EVENT, f: (EVENT) => Unit): Unit;
}
