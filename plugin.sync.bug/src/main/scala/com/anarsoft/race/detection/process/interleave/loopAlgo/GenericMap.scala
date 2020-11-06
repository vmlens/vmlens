package com.anarsoft.race.detection.process.interleave.loopAlgo

import java.util.ArrayList


class GenericMap[ELEMENT]( val create : ( Int ) => ELEMENT ) {
  
  
  val list = new ArrayList[ELEMENT];
  
  
  def getOrCreate( id : Int ) =
  {
    
    while(  list.size() <= id )
    {
      list.add(  create(  list.size()  ) )
    }
    
    
    list.get(id);
    
  }
  
  
  
}