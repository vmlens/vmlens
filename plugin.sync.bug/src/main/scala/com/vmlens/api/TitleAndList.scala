package com.vmlens.api

class TitleAndList[T](val title : String, val list : Seq[T]) {
  
  def  map[N]( f : T => N  ) = new TitleAndList[N](title, list.map(f) );
   
  
  
}