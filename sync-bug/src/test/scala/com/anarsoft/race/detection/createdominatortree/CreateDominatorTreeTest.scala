package com.anarsoft.race.detection.createdominatortree

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CreateDominatorTreeTest extends AnyFlatSpec with Matchers {

  "CreateDominatorTree " should "return the same tree for a tree with one branch" in {
    // Given
    val context = CreateGraphStackContext();
    val builder = TreeTestBuilder(context, 1);
    builder.method(1, () => {
      builder.method( 2, () => {
        builder.syncBlock(3, () => {})
      })
    });
   
    // When 
    val dominatorTree = CreateDominatorTreeFromRunData.dominatorTree(context.root,context.graph)
    
    // Then
    CompareGraph.shouldBe(dominatorTree, context.graph);
  }
  
  
}
