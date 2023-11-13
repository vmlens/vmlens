package com.anarsoft.race.detection.process

import com.anarsoft.race.detection.variable.{ExclusiveVariableBuilder, GuineaPigA, GuineaPigB}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MainProcessTest extends AnyFlatSpec with Matchers {

  "The main process" should "read, process and iterate in a loop" in {
    // Variables
    val firstVariable = new ExclusiveVariableBuilder[GuineaPigA]();
    val secondVariable = new ExclusiveVariableBuilder[GuineaPigB]();

    // Source
    

    // Transform
    // Loop

    // Process

  }

}
