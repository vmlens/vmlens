package com.vmlens.api.internal.reports

/**
 * 
 * entweder ein container 
 * 
 * 
 */
import com.vmlens.api.Icon
import com.vmlens.api.internal.IconRepository


trait ReportElement[CONTEXT] {
  
  

  def initialize( contextReport : CONTEXT );
  def imagePath(icon: Icon) = IconRepository.imagePath(icon);
  
  
  def name() : String;
  
  
  def breakableName() = Model2View.makeBreakable(name());
  
  
}