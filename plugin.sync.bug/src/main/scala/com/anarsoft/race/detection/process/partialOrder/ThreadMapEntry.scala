package com.anarsoft.race.detection.process.partialOrder

import java.util.TreeMap
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.model.WithStatementPosition
import com.anarsoft.race.detection.process.monitorRelation.ListBasedMap
import com.anarsoft.race.detection.process.monitorRelation.ListBasedMapEntry
import com.typesafe.scalalogging.Logger
import java.util.ArrayList
// ListBasedMap[Int]

class ThreadMapEntry() {

   val logger = Logger(classOf[ThreadMapEntry])
  
  
  var higherThreadIdRight: Option[TreeMap[Int, Int]] = None;
  var higherThreadIdLeft: Option[TreeMap[Int, Int]] = None;
  
  
  
  
  
  
  def toFixed() =
  {
    new ThreadMapEntryFixed( higherThreadIdRight.map(  x =>  toListBasedMap(x) ) ,  higherThreadIdLeft.map(  x =>  toListBasedMap(x) )   );
  }
  
  
  
  def toListBasedMap(treeMap :  TreeMap[Int, Int]) =
  {
    val list = new  ArrayList[ListBasedMapEntry[Int]]( treeMap.size() );
    
    val iter = treeMap.entrySet().iterator();
    
    while( iter.hasNext() )
    {
      val current = iter.next();
      
      list.add(new ListBasedMapEntry[Int]( current.getKey() , current.getValue() ) );
    }
    
    ListBasedMap(list);
    
    
  }
  
  

  def leftComesBeforeRight(key: HigherLowerThreadId, leftPosition: Int, leftThreadId: Long, order: Int) {

    val x =
      if (leftThreadId == key.higherId) {
        higherThreadIdLeft match {
          case None =>
            {
              val n = new TreeMap[Int, Int]
              higherThreadIdLeft = Some(n);
              n
            }

          case Some(x) => x;

        }

      } else {
        higherThreadIdRight match {
          case None =>
            {
              val n = new TreeMap[Int, Int]
              higherThreadIdRight = Some(n);
              n
            }

          case Some(x) => x;

        }
      }

    /*
           * wir müssen hier sicherstellen das niedrige  werte nicht mit höheren
           * werten überschrieben werden.
           *
           * Tritt bei der transitive closure bildung auf wenn über ein monitor mehrere
           * Threads synchronisieren, z.B. bei LeftRightDeadlock
           *
           *
           *
           *
           */

    val current = x.floorEntry(order);
    if (current != null) {

      if (current.getValue < leftPosition) {

        //     println( current + " " + leftPosition );

        x.put(order, leftPosition);
        /*
              *
              * kann auftreten siehe oben
              *
              */
      } else {

      }
    } else {
      x.put(order, leftPosition);
    }

  }

  def removeNonContinuousSyncPoints(map: TreeMap[Int, Int]) {
    val iter = map.entrySet().iterator();
    var previousValue = -1;

    val keysToBeRemoved = new HashSet[Int]();

    while (iter.hasNext()) {
      val current = iter.next();

      if (previousValue > current.getValue) {
        keysToBeRemoved.add(current.getKey);
      } else {
        previousValue = current.getValue();
      }

    }

    for (key <- keysToBeRemoved) {
      map.remove(key);
    }

  }

  def removeNonContinuousSyncPoints() {
    higherThreadIdRight.foreach(x => removeNonContinuousSyncPoints(x))
    higherThreadIdLeft.foreach(x => removeNonContinuousSyncPoints(x))
  }





}