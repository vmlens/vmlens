package com.anarsoft.race.detection.rundata

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RunResultTest extends AnyFlatSpec with Matchers {

  "takeThisInsteadOther " should " take the most interesting part" in {
    val withFailure = new RunResultGuineaPig(true , 0 ,false,  10);
    val withOneDataRace = new RunResultGuineaPig(false , 1 ,false, 5);
    val withFourDataRaces = new RunResultGuineaPig(false, 4, false,5);
    val everythingOk = new RunResultGuineaPig(false, 0, false,9);

    withFailure.takeThisInsteadOther(withOneDataRace) should be(true)
    withOneDataRace.takeThisInsteadOther(withFailure) should be(false)

    withFourDataRaces.takeThisInsteadOther(withOneDataRace) should be(true)
    withOneDataRace.takeThisInsteadOther(withFourDataRaces) should be(false)

    withFailure.takeThisInsteadOther(everythingOk) should be(true)
    everythingOk.takeThisInsteadOther(withFailure) should be(false)

    withFourDataRaces.takeThisInsteadOther(everythingOk) should be(true)
    everythingOk.takeThisInsteadOther(withFourDataRaces) should be(false)
  }

}
