package com.anarsoft.race.detection.createstacktrace

import com.anarsoft.race.detection.event.gen.{MethodEnterEventGen, MethodExitEventGen}
import com.anarsoft.race.detection.util.EventArray
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ServiceCalculateMethodCountToStacktraceNodeTest extends AnyFlatSpec with Matchers {


  "The service " should " calculate the stack trace nodes " in {
    // Expected
    val root = new StacktraceNodeRoot(1);
    val expected = Array(root, new StacktraceNodeIntermediate(5, root), root, root);

    // Given
    val methodEnterRoot = new MethodEnterEventGen(1, 1, 
      0, 0, 0);
    val methodEnterChild = new MethodEnterEventGen(1, 5, 1,
      0, 0);
    val methodExitChild = new MethodExitEventGen(1, 5, 2,
      0, 0);
    val methodExitRoot = new MethodExitEventGen(1, 1, 3,
      0, 0);

    val array = Array(methodEnterRoot, methodEnterChild, methodExitChild, methodExitRoot);

    // When
    val eventArray = new EventArray(array);
    val result = new ServiceCalculateMethodCountToStacktraceNode().process(eventArray);

    // Then
    result.get(1).get should be(expected)
  }


}
