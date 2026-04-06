package com.anarsoft.race.detection.createdominatortree

import scala.collection.mutable

class SetMultimap[KEY,VALUE] {
  
  private val map = new mutable.HashMap[KEY,mutable.HashSet[VALUE]]() 

  def add(key : KEY, value : VALUE ): Unit = {
    val set =
      map.get(key) match {
      case None => {
        val x =  new mutable.HashSet[VALUE]();
        map.put(key,x)
        x;
      }
      case Some(x) => {
        x;
      }
    }
    set.add(value);
  }
  
  def get(key : KEY) : mutable.HashSet[VALUE] = {
    val result = map.get(key);
    result match {
      case None =>{
        new mutable.HashSet[VALUE]();
      }
      case Some(x) => {
        x;
      }
    }
  }
  
  
}
