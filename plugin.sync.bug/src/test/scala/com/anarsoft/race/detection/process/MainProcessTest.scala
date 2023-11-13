package com.anarsoft.race.detection.process

import com.anarsoft.race.detection.process.execute.ExecuteSourceAndTransformationProcess
import com.anarsoft.race.detection.process.source.SourceProcessBuilder
import com.anarsoft.race.detection.process.transformation.TransformationProcessBuilder
import com.anarsoft.race.detection.variable.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable.ListBuffer

class MainProcessTest extends AnyFlatSpec with Matchers {

  "The main process" should "read, process " in {
    // Variables
    val firstVariable = new ExclusiveVariableBuilder[GuineaPigA]();
    val secondVariable = new ExclusiveVariableBuilder[GuineaPigB]();

    // A combined Variable
    val combined = new CombinedExclusiveVariable[GuineaPigTrait](List(firstVariable, secondVariable));
    val firstStep = combined.newId();

    // The result is a shared variable which gets processed outside of the process

    // The source process builder
    val sourceProcessBuilder = new SourceProcessBuilder();

    // First Source
    sourceProcessBuilder.add(new SourceMock(() => {
      val publish = firstVariable.createPublisher(firstStep.getIdFor(firstVariable));
      publish.publish(new GuineaPigA("Erste"));
    }));

    // Second Source
    sourceProcessBuilder.add(new SourceMock(() => {
      val publish = secondVariable.createPublisher(firstStep.getIdFor(secondVariable));
      publish.publish(new GuineaPigB("Zweites"));
    }));

    // The transformer builder
    val transformationProcessBuilder = new TransformationProcessBuilder();

    // Transform
    val secondStep = combined.newId();
    combined.foreach(firstStep, secondStep, (access) => {
      transformationProcessBuilder.add(
        () => {
          if (access.isAvailable()) {
            val value = access.take();
            value.setText("geladen:" + value.getText());
            access.publish();
            true;
          }
          else {
            false;
          }
        }
      )
    });

    val result = new ListBuffer[String]();

    // Create the result (Sink)
    combined.foreach(secondStep, (extract) => {
      transformationProcessBuilder.add(
        () => {
          if (extract.isAvailable()) {
            result.append(extract.take().getText());
            true;
          }
          else {
            false;
          }
        })
    });

    // Build The Process
    val transformationProcess = transformationProcessBuilder.build();
    val sourceProcess = sourceProcessBuilder.build();

    // Execute the Process
    val executeSourceAndTransformationProcess = new ExecuteSourceAndTransformationProcess(sourceProcess, transformationProcess);
    executeSourceAndTransformationProcess.execute();

    result should contain("geladen:Erste")
    result should contain("geladen:Zweites")
  }

}
