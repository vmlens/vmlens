package com.anarsoft.race.detection.createpartialordersyncaction.inttest

import com.anarsoft.race.detection.createpartialordersyncaction.inttest.builder.AtomicNonBlockingTestBuilder
import com.anarsoft.race.detection.createpartialordersyncaction.inttest.generic.ReadOrWrite
import com.anarsoft.race.detection.event.interleave.AtomicNonBlockingEvent
import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElementBuilder
import org.scalatest.flatspec.AnyFlatSpec

class AtomicNonBlockingIntTest extends AnyFlatSpec{

  "atomic non blocking " should "should full fill read or write tests " in {

    new ReadOrWrite[AtomicNonBlockingEvent](new AtomicNonBlockingTestBuilder(65445L),
      GroupInterleaveElementBuilder.create_container_atomic_non_blocking)

  }

}
