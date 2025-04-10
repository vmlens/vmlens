package com.anarsoft.race.detection.createpartialordersyncaction.inttest

import com.anarsoft.race.detection.createpartialordersyncaction.inttest.builder.VolatileFieldAccessTestBuilder
import com.anarsoft.race.detection.createpartialordersyncaction.inttest.generic.ReadOrWrite
import com.anarsoft.race.detection.event.interleave.VolatileFieldAccessEvent
import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElementBuilder
import com.anarsoft.race.detection.sortutil.EventWithReadWriteContainer
import org.scalatest.flatspec.AnyFlatSpec

class VolatileFieldIntTest extends AnyFlatSpec {

  "volatile fields " should "should full fill read or write tests " in {

   new ReadOrWrite[VolatileFieldAccessEvent](new VolatileFieldAccessTestBuilder(5, 65445L),
     GroupInterleaveElementBuilder.create_container_volatile_field)
    
  }  
  
}
