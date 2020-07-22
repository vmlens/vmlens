package com.anarsoft.config

import java.util.Properties

class PropetyContainer2Properties(val properties : Properties) extends PropetyContainer {
  
   def getProperty(key : String)  = properties.getProperty(key);
  
}