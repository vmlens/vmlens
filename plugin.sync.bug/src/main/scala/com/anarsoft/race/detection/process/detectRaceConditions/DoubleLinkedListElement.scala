package com.anarsoft.race.detection.process.detectRaceConditions

class DoubleLinkedListElement[ID_PER_OBJECT,EVENT  <: EventDetectRaceConditions[ID_PER_OBJECT] ](val element : EVENT) {
  
  var previous : Option[DoubleLinkedListElement[ID_PER_OBJECT,EVENT]] = None;
  var next : Option[DoubleLinkedListElement[ID_PER_OBJECT,EVENT]] = None;
  
  
  override def toString() = element.toString()
  
}