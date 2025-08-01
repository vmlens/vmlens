package com.anarsoft.race.detection.process.run

import com.vmlens.report.assertion.{OnDescriptionAndLeftBeforeRight, OnDescriptionAndLeftBeforeRightNoOp, OnEventNoOp}

class ProcessRunContextBuilder {

  private var showAllMemoryAccess = false
  private var showAllRuns: Boolean = false;
  private var txtFormat: Boolean = false;
  private var onDescriptionAndLeftBeforeRight : OnDescriptionAndLeftBeforeRight = new OnDescriptionAndLeftBeforeRightNoOp();

  def withShowAllMemoryAccess(): ProcessRunContextBuilder = {
    showAllMemoryAccess = true;
    this;
  }
  
  def withShowAllRuns() : ProcessRunContextBuilder = {
    showAllRuns = true;
    this;
  }

  def withTxtFormat(): ProcessRunContextBuilder = {
    txtFormat = true;
    this;
  }
  
  def withOnDescriptionAndLeftBeforeRight(onDescriptionAndLeftBeforeRight : OnDescriptionAndLeftBeforeRight) : ProcessRunContextBuilder = {
    this.onDescriptionAndLeftBeforeRight = onDescriptionAndLeftBeforeRight;
    this;
  }
  
  def build() = new ProcessRunContext(onDescriptionAndLeftBeforeRight, 
    new OnEventNoOp(),
    showAllMemoryAccess,
    showAllRuns,
    txtFormat);
  
}
