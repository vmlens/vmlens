package com.anarsoft.race.detection.process.run


class ProcessRunContextBuilder {

  private var showAllMemoryAccess = false
  private var showAllRuns: Boolean = false;
  private var txtFormat: Boolean = false;

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
  

  
  def build() = new ProcessRunContext(
    showAllMemoryAccess,
    showAllRuns,
    txtFormat);
  
}
