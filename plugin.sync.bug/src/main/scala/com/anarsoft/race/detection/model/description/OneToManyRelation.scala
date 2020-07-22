package com.anarsoft.race.detection.model.description

import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.ArrayBuffer;


class OneToManyRelation[KEY,VALUE] {
  
  
  val map = new HashMap[KEY,HashSet[VALUE]];
  
  
  def size = map.size;
  
  
  def put( key : KEY, value : VALUE )
  {
    map.get(key) match
    {
      
      case Some(x) =>
        {
          x.add(value);
        }
      
      case None =>
        {
          val set = new HashSet[VALUE]();
          set.add(value)
          map.put(key,set);
        }
      
      
    }
  }
  
  
  def get(key : KEY) =
  {
     map.get(key)
  }
  
  
  
  def applyToTopNElements(upTo : Int,  f : Count => Unit )
  {
    var topNElements = new ArrayBuffer[Count]();
    
   for( elem <- map )
   {
     val count = elem._2.size;
     
     if( topNElements.size < upTo )
     {
       topNElements.append(new Count(count, elem._1));
       topNElements = topNElements.sortBy( x => x.count  );
     }
     else
     {
       
       
       if( topNElements(0).count <  count )
       {
         topNElements.append(new Count(count, elem._1));
         topNElements = topNElements.sortBy( x => x.count  );
         topNElements.remove( 0  );
       }
       
       
     }
     
   }
   
   for( elem <- topNElements)
   {
     f(elem);
   }
   
    
    
  }
  
  
  
  case class Count(val count : Int, val key : KEY)
  {
    
  }
  
  
  
}