package com.anarsoft.race.detection.process.aggregate.create

import java.util.Comparator

class ComparatorByGroupIds extends Comparator[AggregateGroupList]  {
   def	compare(o1 :  AggregateGroupList,  o2 : AggregateGroupList ) : Int =
    {
       
        val sorted1 = o1.sorted();
        val sorted2 = o2.sorted();
        
        val iter1 = sorted1.iterator;
        val iter2 = sorted2.iterator;
        
        while( iter1.hasNext && iter2.hasNext )
        {
          val c1 = iter1.next();
          val c2 = iter2.next();
          
          if( c1 != c2 )
          {
            return java.lang.Integer.compare( c1 ,  c2)
          }
          
          
          
        }
        
    return java.lang.Integer.compare(  sorted1.size , sorted1.size )
     
     
     
     
    }  
}