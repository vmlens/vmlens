package com.anarsoft.race.detection.process

import com.anarsoft.race.detection.process.source.SourceProcessBuilder
import com.anarsoft.race.detection.process.transformation.TransformationProcessBuilder
import com.anarsoft.race.detection.variable.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MainProcessTest extends AnyFlatSpec with Matchers {

  "The main process" should "read, process " in {
    // Variables
    val firstVariable = new ExclusiveVariableBuilder[GuineaPigA]();
    val secondVariable = new ExclusiveVariableBuilder[GuineaPigB]();

    // A combined Variable
    val combined = new CombinedExclusiveVariable[GuineaPigTrait](List(firstVariable));
    val firstStep = combined.newId();

    // The source process builder
    val sourceProcessBuilder = new SourceProcessBuilder();

    // First Source
    sourceProcessBuilder.add(new SourceMock(() => {
      val publish = firstVariable.createPublisher(firstStep.getIdFor(firstVariable));
      publish.publish(new GuineaPigA());
    }));

    // The transformer builder
    val transformationProcessBuilder = new TransformationProcessBuilder();

    // Transform
    val secondStep = combined.newId();
    combined.foreach(firstStep, secondStep, (access) => {

    });

    // Create the result (Sink)

    // Build The Process


    // Execute the Process

  }

}
