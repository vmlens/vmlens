package com.anarsoft.race.detection.sortutil

trait EventContainer[EVENT] {
  def put(elem: EVENT): EventContainer[EVENT];

  def foreachOpposite(elem: EVENT, f: (EVENT) => Unit): Unit;
}
