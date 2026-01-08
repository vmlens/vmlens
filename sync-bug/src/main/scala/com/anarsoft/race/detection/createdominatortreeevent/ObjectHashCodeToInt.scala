package com.anarsoft.race.detection.createdominatortreeevent

import scala.collection.mutable

class ObjectHashCodeToInt {
  
  private val objectHashCodeToInt = new mutable.HashMap[Long,Int]
  private var maxId = 0;
  
  def get(objectHashCode : Long) : Int = {
    objectHashCodeToInt.get(objectHashCode) match {
      case None => {
        val x = maxId;
        maxId = maxId + 1;
        objectHashCodeToInt.put(objectHashCode,x)
        x
      }

      case Some(x) => x;
    }
  }
  

}
