package com.vmlens.api.internal.reports

import java.io._;

trait HtmlProvider {
 
  def write2Stream(path : String , printStream : PrintWriter);
  
  
}