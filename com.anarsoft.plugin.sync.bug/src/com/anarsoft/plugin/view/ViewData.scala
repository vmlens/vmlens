package com.anarsoft.plugin.view


import  com.vmlens.api.internal.reports.Element4TreeViewer

abstract class ViewData {
  
}

case class ViewStartData() extends ViewData;


case class ViewUrlData(val url : String) extends ViewData;

case class ViewTreeData(val elements : Seq[Element4TreeViewer]) extends ViewData;