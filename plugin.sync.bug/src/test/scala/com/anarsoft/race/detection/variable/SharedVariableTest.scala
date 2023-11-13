package com.anarsoft.race.detection.variable

import com.anarsoft.race.detection.variable.{SharedVariable, SharedVariableBuilder}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SharedVariableTest extends AnyFlatSpec with Matchers {

  "SharedVariable" should "be available to all variables" in {
    // Given
    val builder = new SharedVariableBuilder[String]();
    val firstExtractor = builder.createExtractor();
    val secondExtractor = builder.createExtractor();
    val publisher = builder.createPublisher();

    // Then
    firstExtractor.isAvailable() should be(false)

    // When
    publisher.publish("hallo");

    // Then
    firstExtractor.isAvailable() should be(true)
    secondExtractor.isAvailable() should be(true)

    // When
    firstExtractor.take() should be("hallo");
    secondExtractor.take() should be("hallo");

    // Then
    firstExtractor.isAvailable() should be(false)
  }

  it should "be empty after all values were taken out" in {
    // Constants
    val first = new VariableId()
    val second = new VariableId();

    // Given
    val sharedVariable = new SharedVariable[String]();

    // Then
    sharedVariable.isAvailable(first) should be(false);

    // When
    sharedVariable.requiredBy(first);
    sharedVariable.requiredBy(second);

    sharedVariable.put("test");

    // Then
    sharedVariable.isAvailable(first) should be(true);

    // When
    sharedVariable.take(first) should be("test")
    sharedVariable.take(second) should be("test")

    // Then
    sharedVariable.isAvailable(first) should be(false);
  }
}
