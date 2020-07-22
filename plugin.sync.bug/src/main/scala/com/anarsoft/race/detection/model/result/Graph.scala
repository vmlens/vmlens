package com.anarsoft.race.detection.model.result

import com.netflix.nfgraph.compressed.NFCompressedGraph;
import com.netflix.nfgraph.OrdinalIterator;
import com.netflix.nfgraph.NFGraph;


trait Graph {
  
  def graph : NFGraph;
  
  def foreachOrdinal(nodeTyp : String, nodeOrdinal : Int, relation : String, f : Int => Unit  )
  {
     val iter = graph.getConnectionIterator(nodeTyp , nodeOrdinal  ,relation);
    
    var current = iter.nextOrdinal();
    
    while( current != OrdinalIterator.NO_MORE_ORDINALS )
    {
      
      f( current );
      
      current = iter.nextOrdinal();
    }
  }
  
  
  

  
  
  
  
}