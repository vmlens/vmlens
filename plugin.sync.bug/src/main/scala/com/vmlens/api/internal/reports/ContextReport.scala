package com.vmlens.api.internal.reports

class ContextReport( val  issueDetailLinks : LinkProvider , 
    val elementDetailLink : LinkProvider,
    val inMaven : Boolean, 
    val showChildLinks : Boolean,
    val titleIndex: String,
    val titleElements: String,
    val fileNameElements : String) {
  
}