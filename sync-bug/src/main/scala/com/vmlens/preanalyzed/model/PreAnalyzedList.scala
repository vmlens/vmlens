package com.vmlens.preanalyzed.model

case class PreAnalyzedList(list : List[PreAnalyzed]) extends PreAnalyzedOrList {

  override def asList(): List[PreAnalyzed] = list;
}
