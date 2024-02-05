package com.anarsoft.race.detection.createPartialOrder

import com.anarsoft.race.detection.processEventByType.EventWithType
import com.anarsoft.race.detection.sortUtil.SortableEvent

trait SyncActionEvent[EVENT] extends EventWithType[EVENT] with SortableEvent {

}
