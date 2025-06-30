package com.vmlens.preanalyzed.model

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PackageOrClass

trait ClassModel extends PreAnalyzedOrList {

  def asList() : List[ClassModel] = List(this);
  def take(className : String) : Boolean;
  def create() : PackageOrClass;
  
}




















