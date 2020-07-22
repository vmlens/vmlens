package com.anarsoft.race.detection.process.aggregate.create

import scala.collection.mutable.HashSet

class AggregateGroupList {
  
  val groupSet = new HashSet[Int]
  var ordinal : Int = -1;
  
  var sortedInternal : Option[List[Int]] = None;
  
  
  def sorted() =
  {
    
    sortedInternal match
    {
      
      case None =>
        {
          val sort = groupSet.toList.sorted;
          
          
          sortedInternal = Some(sort);
          
          sort;
          
          
        }
      
      case Some(x) => x;   
      
      
    }
    
  }
  
  
  
  
}