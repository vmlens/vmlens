package com.anarsoft.race.detection.createpartialordersyncaction.inttest.generic

import com.anarsoft.race.detection.createlastthreadposition.LastThreadPositionMap
import com.anarsoft.race.detection.createpartialordersyncaction.{AddToPartialOrderBuilder, SyncActionEventWithCompareType}
import com.anarsoft.race.detection.partialorder.{BuildPartialOrderContextImpl, PartialOrderContainer, PartialOrderImpl}
import com.anarsoft.race.detection.sortutil.EventContainer
import com.anarsoft.race.detection.util.EventArray
import com.vmlens.report.assertion.OnDescriptionAndLeftBeforeRightNoOp
import org.scalatest.matchers.should.Matchers

import scala.reflect.ClassTag

class ReadOrWrite[EVENT  <: SyncActionEventWithCompareType[EVENT]](val builder : ReadOrWriteEventBuilder[EVENT],
                                                                  val createContainer: (EVENT) => EventContainer[EVENT]) extends Matchers {

  def multipleReadsOneWrite()(implicit ct: ClassTag[EVENT]): Unit = {
    // Given
    val read_1 = builder.read(1);
    val read_2 = builder.read(2);
    
    val write_3 = builder.write(3);
    
    val array = new EventArray(Array(read_1, read_2,write_3));
    val lastThreadPositionMap = new LastThreadPositionMap();
    val partialOrderContainer = new PartialOrderContainer(new OnDescriptionAndLeftBeforeRightNoOp());
    val buildPartialOrderContext = new BuildPartialOrderContextImpl(partialOrderContainer, lastThreadPositionMap);
    
    // When
    new AddToPartialOrderBuilder[EVENT](createContainer).process(array,buildPartialOrderContext)
    val partialOrder = new PartialOrderImpl(partialOrderContainer);
    
    // Then
    partialOrder.isLeftBeforeRight(read_1,write_3) should be(true)
  }
  
}
