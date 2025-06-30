package com.vmlens.preanalyzed.model

case class PreAnalyzedList(list : List[ClassModel]) extends PreAnalyzedOrList {

  override def asList(): List[ClassModel] = list;
}
