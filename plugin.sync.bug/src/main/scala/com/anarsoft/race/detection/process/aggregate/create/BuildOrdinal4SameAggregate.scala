package com.anarsoft.race.detection.process.aggregate.create

import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.process.aggregate.SameAggregateId
import scala.collection.mutable.HashMap
import java.util.ArrayList
import com.anarsoft.race.detection.process.SortArrayList


object BuildOrdinal4SameAggregate {
  
  
 
  
    
  
  def create( sameAggregateId : HashSet[SameAggregateId], currentOrdinalId :  MutableInt) =
  {
    val aggregateId2Group = new HashMap[Int,AggregateGroupList]
    
    var currentGroup = 0;
    
    
    for( elem <- sameAggregateId )
    {
      
    aggregateId2Group.get( elem.higher ) match
    {
      case Some(group) =>
        {
          aggregateId2Group.get( elem.lower ) match
           {
            case Some(other) =>
              {
               
                group.groupSet.add(currentGroup);
                other.groupSet.add(currentGroup);
                
                
                currentGroup = currentGroup  + 1;
                
                               
              }
            
            case None =>
              {
                  aggregateId2Group.put( elem.lower  , group) 
              }
            
           }
        }
      
      case None =>
        {
          aggregateId2Group.get( elem.lower ) match
           {
            case Some(other) =>
              {
                   aggregateId2Group.put( elem.higher  , other) 
              }
            
            case None =>
              {
                val newGroup = new AggregateGroupList();
                newGroup.groupSet.add(currentGroup);
                
                 currentGroup = currentGroup  + 1;
                aggregateId2Group.put( elem.higher  , newGroup) 
                aggregateId2Group.put( elem.lower  , newGroup) 
              }
            
           }
        }
        
      
    }
      
      
    }
    
    
    val list = new ArrayList[AggregateGroupList]();
    
    for( elem <- aggregateId2Group )
    {
      list.add(elem._2);
    }
    
    
    SortArrayList.sort( list , new ComparatorByGroupIds() );
    
    var prevoiusGroupIds = new HashSet[Int]
    val iter = list.iterator();
    var currentId = -1;
    
    
    
    while( iter.hasNext() )
    {
      val current = iter.next();
      var contained = false;
      
      for( x <- current.groupSet )
      {
        if(prevoiusGroupIds.contains(x))
        {
          contained = true;
        }
        
        
      }
   
      
      if( ! contained  )
      {
        currentId = currentOrdinalId.getAndIncrement();
        prevoiusGroupIds = new HashSet[Int]
        
      }
      
      
      current.ordinal = currentId;
      
      
      
      for( x <- current.groupSet )
      {
        prevoiusGroupIds.add(x)
      }
      
      
      
    }
    
 
      
    
    
    

    aggregateId2Group
    
    
    
  }
  
  
  
  
  def main(args : Array[String])
  {
    
    val same = new HashSet[SameAggregateId]
    same.add(  SameAggregateId( 1 , 5  ) )
    same.add(  SameAggregateId( 5 , 8  ) )
     same.add(  SameAggregateId( 8 , 1  ) )
    same.add(  SameAggregateId( 2 , 7  ) )
    
    
    var result = create(same,new MutableInt(0));
    var mutableInt = new  MutableInt ( 0 );
    
    for( elem <- result )
    {
      
      println( elem._1 + " " + elem._2.ordinal )
      
    }
      
    
    
    
  }
    
  
  
  
  
  
  
  
  
  
  
  
  
}