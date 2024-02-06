package com.anarsoft.race.detection.sortUtil

trait EventContainer[EVENT] {
  def create(elem: EVENT): EventContainer[EVENT];

  def foreachOpposite(elem: EVENT, f: (EVENT) => Unit): Unit;
}
