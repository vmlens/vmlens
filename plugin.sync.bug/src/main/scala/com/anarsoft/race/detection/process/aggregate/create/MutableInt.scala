package com.anarsoft.race.detection.process.aggregate.create

class MutableInt ( var value : Int ) {
  
  
  
  def getAndIncrement() =
  {
    val t = value;
    value = value + 1;
    t;
  }
}