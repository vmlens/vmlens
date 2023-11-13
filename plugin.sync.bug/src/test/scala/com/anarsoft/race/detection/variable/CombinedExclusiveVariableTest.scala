package com.anarsoft.race.detection.variable

import com.anarsoft.race.detection.variable.CombinedExclusiveVariable
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CombinedExclusiveVariableTest extends AnyFlatSpec with Matchers {

  "A CombinedExclusiveVariable" should "combine two variables" in {
    // Constants
    // val first = new CombinedExclusiveVariableId();
    // val second = new CombinedExclusiveVariableId();


    // Given
    val firstVariable = new ExclusiveVariableBuilder[String]();
    val secondVariable = new ExclusiveVariableBuilder[String]();

    //val combinedVariable = new CombinedExclusiveVariable[GuineaPigTrait]();

    //val combinedVariableId = combinedVariable.newId();

    // during build process of the process
    //to build process steps for each variable
    //combinedVariable.foreachIntermediate(first,second);


  }


}
