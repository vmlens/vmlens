package com.anarsoft.plugin.sync.bug

import com.anarsoft.integration._;

object TextRepositoryEclipse extends TextRepository {
  

  val TILE_NEW_ELEMENT = "Add method";
  
  val DESCRIPTION_NEW_ELEMENT = 
"""vmlens supports the following wild cards: 

*  Matches zero or more characters
      (not including the path separator .)
** Matches zero or more path segments.
?  Matches one character 
     (any character except the path separator .)

Examples:
com.vmlens.** 
    Matches all methods from classes in the package 
    and sub-packages from com.vmlens
com.vmlens.test.TestAllRaces.* 
    Matches all methods  in the 
    class com.vmlens.test.TestAllRaces
""";
  
  
    val TILE_EDIT_ELEMENT = "Edit method";
  
  val DESCRIPTION_EDIT_ELEMENT = "Edit a method";

//  val LABEL_NEW_ELEMENT = "LABEL_NEW_ELEMENT";
  
  
  val RUN_DIALOG_TITLE_ALL_APPLICATIONS = "Trace All Applications";
  
  val RUN_DIALOG_MESSAGE = "Use the vmlens agent to trace an java application.";
  
  val RUN_DIALOG_LABEL_LOCAL_TRACE = "Add the following string to the vm arguments of your application and press ok:";
  val RUN_DIALOG_LABEL_EXTERNAL_TRACE = "Or use the export button to trace an application on a remote machine";
  
  
}