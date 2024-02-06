package com.anarsoft.race.detection.createPartialOrder

import com.anarsoft.race.detection.processEventByType.EventWithType
import com.anarsoft.race.detection.util.WithPosition


trait SyncActionEvent[EVENT] extends EventWithType[EVENT] with WithPosition {

}
