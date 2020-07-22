package com.anarsoft.plugin.sync.bug

import org.apache.commons.io.FileUtils
import java.io._;


object CopyWebFiles {
  
  
  def copy()
  {
    
     FileUtils.copyDirectory(new File("icons/img/") , new File("../plugin.sync.bug/src/main/resources/copy/mvnHtmlReport/img/"));
    
    
    FileUtils.copyDirectory(new File("../plugin.sync.bug/src/main/resources/copy/mvnHtmlReport/") , new File("web_files/"));
  }
  
  
   def main(args: Array[String])
  {
    copy();
  }
  
  
}