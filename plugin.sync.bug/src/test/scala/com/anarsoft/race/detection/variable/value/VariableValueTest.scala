package com.anarsoft.race.detection.variable.value

import com.anarsoft.race.detection.variable.SharedVariableId
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class VariableValueTest extends AnyFlatSpec with Matchers {

  "A variable value " should "go from empty to full to closed" in {
    // Constants
    val first = SharedVariableId();
    val second = SharedVariableId();

    // Given
    val variableValue = new VariableValue[String]();

    // Then
    variableValue.isAvailable() should be(false);

    // When
    variableValue.requiredBy(first);
    variableValue.requiredBy(second);
    variableValue.put("hallo");

    // Then
    variableValue.isAvailable() should be(true);

    // When
    variableValue.take(second) should be("hallo")
    variableValue.take(first) should be("hallo")

    // Then
    variableValue.isAvailable() should be(false);

  }

}
