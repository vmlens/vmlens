package com.anarsoft.race.detection.process.mode.state

import com.anarsoft.race.detection.model.result._;
import scala.collection.mutable.ArrayBuffer
import java.util.StringTokenizer
import scala.collection.mutable.HashSet


class ListOfNameAndState(val list: ArrayBuffer[NameAndState]) {

  def done() = list.length < 2;

  def add(state: NameAndState) {
    list.append(state)
  }
  
   def createSharedState() =
   {

     if(  list.length == 1 )
     {
       list(0).sharedState;
     }
     else
     {
       createSharedState4List()
     }
     
     
   }
  

  def createSharedState4List() = {

    var maxPackageName = "";
    var currentName: Option[String] = None;
    var same = true;


    
    val newTokenizerList = list.map(x => new StringTokenizer(x.sharedState.classOrPackageName, "."))

    while (same) {

      same = true;
      currentName = None;

      for (elem <- newTokenizerList) {
        if (elem.hasMoreTokens()) {
          val token = elem.nextToken();

          currentName match {
            case Some(x) =>
              {
                if (x != token) {
                  same = false;
                }
              }
            case None =>
              {
                currentName = Some(token);
              }
          }

        } else {
          same = false;
        }
      }

      if (same) {
        currentName match {
          case Some(x) =>
            {
              if (maxPackageName == "") {
                maxPackageName = x;
              } else {
                maxPackageName = maxPackageName + "." + x;
              }
            }

          case None =>
            {

            }

        }
      }

    }
    
    val memoryAggregateSet = new HashSet[Int]()
        
    for(elem <-   list )
    {
       for( x <- elem.sharedState.memoryAggregateSet )
       {
         memoryAggregateSet.add(x);
       }
    }
        
    
    

    new SharedStatePackage(maxPackageName,memoryAggregateSet.toSet);
  }

  def seperate() = CreateGroupsByPackageNameAlgo.seperate(list);

}

object ListOfNameAndState {

  def apply(state: NameAndState) =
    {
      val list = new ArrayBuffer[NameAndState]
      list.append(state)

      new ListOfNameAndState(list);
    }

}