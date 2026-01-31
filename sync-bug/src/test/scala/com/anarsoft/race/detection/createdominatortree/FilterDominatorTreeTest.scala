package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.testfixture.dominatortree.DominatorTreeObjectMother
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FilterDominatorTreeTest extends AnyFlatSpec with Matchers {

  "FilterDominatorTree" should "filter branchs lading to non leaf nodes" in {
    val filtered = new FilterDominatorTree().filter(DominatorTreeObjectMother.dominatorTreeOneLock(),
      DominatorTreeObjectMother.ROOT);

    CompareGraph.shouldBe(filtered, DominatorTreeObjectMother.filteredDominatorTreeOneLock())

  }
}
