package com.anarsoft.race.detection.process.partialOrder


import scala.collection.mutable.ArrayBuffer



/**
 * 
 * Based on https://en.wikipedia.org/wiki/Binary_search_algorithm
 * 
 */


class ListBasedMap4Equals(val list : ArrayBuffer[ListBasedMapEntry4Equals]) {
  
  
  /*

    
    
    The procedure may return any index whose element is equal to the target value, even if there are duplicate elements in the array. For example, 
    if the array to be searched was [ 1 , 2 , 3 , 4 , 4 , 5 , 6 , 7 ] {\displaystyle [1,2,3,4,4,5,6,7]} {\displaystyle [1,2,3,4,4,5,6,7]} and the target was 4 {\displaystyle 4} 4, 
    then it would be correct for the algorithm to either return the 4th (index 3) or 5th (index 4) element. The regular procedure would return the 4th element (index 3) in this case. 
    It does not always return the first duplicate (consider [ 1 , 2 , 4 , 4 , 4 , 5 , 6 , 7 ] {\displaystyle [1,2,4,4,4,5,6,7]} {\displaystyle [1,2,4,4,4,5,6,7]} which still returns the 4th element).
   * 
   * 
   * 
   */
  
  
  
  def onElements(key : Long , f : ( ListBasedMapEntry4Equals  ) => Unit )
  {
      searchPosition(key) match
      {
        
        case None =>
          {
            
          }
        case Some(x) =>
          {
            
            var index = x;
            
            while(  index > 0 && list(index).key == key )
            {
              f( list(index) );
              
              index = index -1;
              
            }
            
            
            index = x + 1;
            
               while(  index < list.size && list(index).key == key )
            {
              f( list(index) );
              
              index = index  + 1;
              
            }
             
          }
       
          
      }
      
      
      
  }
  
  /*
   *    * function binary_search(A, n, T) is
    L := 0
    R := n − 1
    while L ≤ R do
        m := floor((L + R) / 2)
        if A[m] < T then
            L := m + 1
        else if A[m] > T then
            R := m - 1
        else:
            return m
    return unsuccessful
   * 
   * 
   */
  
  
  def searchPosition(key : Long) : Option[Int]  =
  {
    var start = 0;
    var end =  list.size - 1;
    var middle = 0;
    var found = false;
    
     while(  start <= end  )
    {
        middle = start + ((end - start) / 2);

       if( list(middle).key  < key )
       {
         start = middle + 1;      /* im rechten Abschnitt weitersuchen */
    
       }
       else    if (list(middle).key > key)
       {
         end = middle - 1;     /* im linken Abschnitt weitersuchen */
       }
       else
       {
         return Some(middle);
       }
       
          
    }
     
     return None;
     
  }
  
  
  
 
  
  
  
}


object ListBasedMap4Equals
{
  
  def apply(list : ArrayBuffer[ListBasedMapEntry4Equals]) =
  {
    
   new  ListBasedMap4Equals(list) ;
  }
  
}


class ListBasedMapEntry4Equals(val key : Long , val value : Long,val threadMapEntry : ThreadMapEntryFixed)
{
  override def toString() = key + ":" + value
  
}