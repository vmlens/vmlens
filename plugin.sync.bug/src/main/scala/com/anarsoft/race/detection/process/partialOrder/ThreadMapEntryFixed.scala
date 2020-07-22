package com.anarsoft.race.detection.process.partialOrder

import com.anarsoft.race.detection.process.monitorRelation.ListBasedMap
import com.anarsoft.race.detection.model.WithStatementPosition
import com.anarsoft.race.detection.model.WithStatementPositionImpl

class ThreadMapEntryFixed(val higherThreadIdRight: Option[ListBasedMap[Int]], val higherThreadIdLeft: Option[ListBasedMap[Int]]) {

  def isLeftBeforeRight(key: HigherLowerThreadId, left: WithStatementPosition, rightPosition: Int) =
    {
      val treeMap =
        if (left.threadId == key.higherId) {
          higherThreadIdLeft

        } else {
          higherThreadIdRight
        }

      treeMap match {

        case None =>
          {
            false;
          }

        case Some(x) =>
          {

            x.getKeyAndValue(rightPosition) match {

              case None           => false;

              case Some(maxEntry) => (left.programCounter <= maxEntry.value)

            }

          }

      }

    }

  def getHighestLeft(leftThreadId: Long, right: WithStatementPosition) =
    {

      val treeMap =
        if (right.threadId > leftThreadId) {

          higherThreadIdRight

        } else {

          higherThreadIdLeft
        }

      treeMap match {
        case None => None;

        case Some(x) =>
          {
            //                val maxEntry =   ;
            //
            //                if(maxEntry == null)
            //                {
            //                  None;
            //                }
            //                else
            //                {
            //                  Some( new WithStatementPositionImpl(   leftThreadId , maxEntry.getValue ) );
            //                }

            x.getKeyAndValue(right.programCounter).map(x => new WithStatementPositionImpl(leftThreadId, x.value))

          }

      }

    }

}