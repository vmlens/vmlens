package com.anarsoft.race.detection.variable

class GuineaPigB(var text: String) extends GuineaPigTrait {
  def getText() = text;

  override def setText(in: String): Unit = {
    text = in;
  }
}
