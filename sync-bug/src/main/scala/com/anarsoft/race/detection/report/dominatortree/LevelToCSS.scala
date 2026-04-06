package com.anarsoft.race.detection.report.dominatortree

class LevelToCSS {
  
  
  def getCss(level : Int) : String = {
    """ style="text-indent: """  + (level * 2) + """ch;" """
  }

}