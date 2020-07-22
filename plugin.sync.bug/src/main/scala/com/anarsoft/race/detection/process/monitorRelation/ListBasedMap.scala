package com.anarsoft.race.detection.process.monitorRelation

import java.util.ArrayList


/**
 * 
 * Based on https://en.wikipedia.org/wiki/Binary_search_algorithm
 * 
 */


class ListBasedMap[VALUE](val list : ArrayList[ListBasedMapEntry[VALUE]]) {
  
  
  
  def getKeyAndValue(key : Int ) =
  {
    
  
     if(list.size() == 0)
    {
      None;
    }
    else
    {
       if( list.get( 0 ).key >= key )
       {
         
         None
       }
       else if (  list.get( list.size() - 1 ).key < key  )
       {
         Some( list.get( list.size() - 1 ));
       }
       else  
       {
         search(key);
         
       }
       
   
      
      
      
    }  
  }
  
  

  
  
  /**
   * 
   * 
   * Ich benötige den größten Wert der kleiner ist als key
   * 
   * bei 
   * 
   * 9 23 55
   * 
   * 
   * bei 7 None;
   * 
   * 
   * 
   * 
   */
  
  
  def get( key : Int )  =
  {
    
   getKeyAndValue(key) match
   {
     
     case None => None;
     
     case Some(x) => Some(x.value);
     
     
     
   }
  
  }
  
  
  
  def search(key : Int) =
  {
    
    var start = 0;
    var end =  list.size() - 1;
    var middle = 0;
    var found = false;
    
     while( ! found)
    {
        middle = start + ((end - start) / 2); /* Bereich halbieren */

       
    
       
            if (list.get(middle).key >= key)
                {
                 end = middle - 1;     /* im linken Abschnitt weitersuchen */
                 if(   list.get(middle - 1 ).key  < key )
                 {
                   found = true;
                 }
                
                }
            else /* (M[mitte] < suchwert) */
            {
               start = middle + 1;      /* im rechten Abschnitt weitersuchen */
             
            }
            /* end if */
        /* end if */
    }
    
    
    
    
    
    Some( list.get( middle -1 ) );
    
    
    
  }
  
  
  
  
  
  
}


object ListBasedMap
{
  
  def apply[VALUE](list : ArrayList[ListBasedMapEntry[VALUE]]) =
  {
   
//    val middle = 
//   if( list.size() % 2 == 1 ) 
//   {
//     (list.size() / 2).asInstanceOf[Int] + 1;
//   }
//   else
//   {
//      (list.size() / 2).asInstanceOf[Int]
//   }
    
   new  ListBasedMap(list) ;
  }
  
}


case class ListBasedMapEntry[VALUE](val key : Int , val value : VALUE)
{
  
  
}