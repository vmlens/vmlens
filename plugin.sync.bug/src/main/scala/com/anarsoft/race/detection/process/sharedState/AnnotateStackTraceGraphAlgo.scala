package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result.StackTraceGraph
import com.anarsoft.race.detection.model.result.StackTraceOrdinal
import scala.reflect.ClassTag
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.IntConsumer
import com.anarsoft.race.detection.model.graph.ModelKey2OrdinalMap


abstract class AnnotateStackTraceGraphAlgo[DATA <: AbstractStackTraceData with Object](implicit c: ClassTag[DATA]) {

  def create(ordinal: Int,stackTraceGraph: StackTraceGraph, packageName2Ordinal : ModelKey2OrdinalMap[String]): DATA;
  def addFromChild(parent : DATA,child : DATA);
  
  

  def annotate(stackTraceGraph: StackTraceGraph, packageName2Ordinal : ModelKey2OrdinalMap[String]) = {

    val array = Array.ofDim[DATA](stackTraceGraph.maxStackTraceOrdinal +1)
    val notDone = new RoaringBitmap();

    for (i <- 0 until array.length) {
      var hasChilds = false;

      stackTraceGraph.foreachStackTraceNodeChild(
        new StackTraceOrdinal(i),
        (x) =>
          {
            hasChilds = true;
          })

      array(i) = create(i,stackTraceGraph,packageName2Ordinal);

      if (hasChilds) {
        notDone.add(i);
        array(i).visited = false;
      }

    }

    var currentBitmap = notDone;

    while (!currentBitmap.isEmpty()) {
      val nextMap = new RoaringBitmap();

      currentBitmap.forEach(new IntConsumer() {
        def accept(ordinal: Int) {
          var allVisited = true;

          stackTraceGraph.foreachStackTraceNodeChild(new StackTraceOrdinal(ordinal), (x) =>
            {
              allVisited = allVisited & array(x.ordinal).visited;
            })

          if (allVisited) {

            stackTraceGraph.foreachStackTraceNodeChild(new StackTraceOrdinal(ordinal), (x) =>
              {
                 addFromChild ( array(ordinal) , array( x.ordinal ));

              })

            array(ordinal).visited = true;

          } else {
            nextMap.add(ordinal)
          }

        }
      })

      currentBitmap = nextMap;

    }

    array;
    
  }
  
  
   def addAll2RoaringBitmap(addTo : RoaringBitmap , from : RoaringBitmap)
  {
    from.forEach( new IntConsumer()
    {
        def accept(ordinal: Int) {
          
          addTo.add(ordinal);
          
        }
    }
    
    
    
    )
    
    
  }
  

}