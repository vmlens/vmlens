package com.anarsoft.race.detection.sortUtil

import com.anarsoft.race.detection.util.WithPosition

trait SortableEvent extends WithPosition {
  def isLeftOnly(): Boolean;

  def isRightOnly(): Boolean;
}
