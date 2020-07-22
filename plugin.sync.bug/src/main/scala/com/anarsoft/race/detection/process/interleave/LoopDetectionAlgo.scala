package com.anarsoft.race.detection.process.interleave

import java.util.List
import java.util.ArrayList

object LoopDetectionAlgo {

  def detectLoop(list: List[InterleaveEventStatement], result: ArrayList[Either[InterleaveEventStatement,StatementLoop]]) {

    var currentList = list;

    while (currentList.size() >= 1) {
      val newIndex = detectLoopFor(currentList, result);
      
      
      currentList = currentList.subList(newIndex,  currentList.size() )
    }

  }

  def detectLoopFor(list: List[InterleaveEventStatement], result: ArrayList[Either[InterleaveEventStatement,StatementLoop]]) =
    {

      var i = 1;
      var loopTill = -1;

      if(list.get(0).canStartLoop())
      {
        
      
      
      while (i < list.size() && loopTill == -1) {
        if (list.get(0).isSameStatement(list.get(i)) ) {
          var firstIndex = 0;
          var secondIndex = i;
          var loopCount = 1;

          while (secondIndex < list.size() && list.get(firstIndex).isSameStatement(list.get(secondIndex))) {
            firstIndex = firstIndex + 1;
            secondIndex = secondIndex + 1;

            if (firstIndex == i) {

              // one complete loop
              firstIndex = 0;
              loopCount = loopCount + 1;
              loopTill = secondIndex;
            }
          }

          if (loopTill != -1) {
            val loopList = new ArrayList[InterleaveEventStatement];

            for (loopIndex <- 0 until i) {
              loopList.add(list.get(loopIndex));
            }

            result.add(Right(new StatementLoop(loopCount, loopList)));

          }
        }
        i = i + 1;

      }

      }
      
      if (loopTill == -1) {
        result.add(Left(list.get(0)));
        1;
      } else {
        loopTill
      }

    }

//  def main(args: Array[String]) {
//    val list = new ArrayList[Statement4LoopDetection]
//    list.add(new Statement4LoopDetection(7))
//    //    list.add(new Statement4LoopDetection(9))
//    list.add(new Statement4LoopDetection(1))
//    list.add(new Statement4LoopDetection(1))
//    //    list.add(new Statement4LoopDetection(1))
//    //    list.add(new Statement4LoopDetection(1))
//    //   list.add(new Statement4LoopDetection(1))
//    //    list.add(new Statement4LoopDetection(7))
//    list.add(new Statement4LoopDetection(9))
//
//    val result = new ArrayList[StatementOrLoop]
//
//    detectLoop(list, result);
//
//    println(result);
//
//  }

}