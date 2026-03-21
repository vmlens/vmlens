package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FilterDominatorTreeAddToFilteredTest extends AnyFlatSpec with Matchers {

  "addToFiltered " should "add all elements to the filtered graph with one branch" in {
    // Given
    val context = CreateGraphStackContext();
    val builder = TreeTestBuilder(context,1);
    var vertex : DominatorTreeVertex = null;
    builder.method(1, () => {
       vertex = builder.syncBlock(3, () => {} )
    }  );
    val filter = new FilterDominatorTree(context.root,context.graph);

    // When
    filter.addToFiltered(vertex);
    
    // Then
    CompareGraph.shouldBe(filter.filtered,context.graph);
  }

  "addToFiltered " should "add all elements to the filtered graph with two branches" in {
    // Given
    val context = CreateGraphStackContext();
    val builder = TreeTestBuilder(context, 1);
    var firstVertex: DominatorTreeVertex = null;
    var secondVertex: DominatorTreeVertex = null;
    builder.method(1, () => {
      firstVertex = builder.syncBlock(3, () => {})
      secondVertex = builder.syncBlock(7, () => {})
    });
    val filter = new FilterDominatorTree(context.root, context.graph);

    // When
    filter.addToFiltered(firstVertex);
    filter.addToFiltered(secondVertex);
    
    // Then
    CompareGraph.shouldBe(filter.filtered, context.graph);
  }

}
