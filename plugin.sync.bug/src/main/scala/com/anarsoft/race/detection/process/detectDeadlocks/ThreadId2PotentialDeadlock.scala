package com.anarsoft.race.detection.process.detectDeadlocks

import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet


class ThreadId2PotentialDeadlock {
  
  val map = new HashMap[Long,HashMap[Int,HashSet[Int]]]();
  
  def add( threadId : Long, parent : Int, child : Int )
  {
    val child2Parent = map.getOrElseUpdate( threadId , new HashMap[Int,HashSet[Int]]());
    
    val parents = child2Parent.getOrElseUpdate(child, new HashSet[Int]);
    
    parents.add( parent );
    
  }
  
  
  def getMatcher( threadId : Long) =
  {
    map.get( threadId ) match
    {
      
      case None =>
        {
          None;
        }
      
      case Some(x) =>
        {
          Some(new PotentialDeadlockMatcher(x));
        }
        
      
    }
  }
  
  
  
  
}