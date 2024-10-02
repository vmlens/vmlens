package com.anarsoft.race.detection.createpartialorder

import com.anarsoft.race.detection.processeventbytype.EventWithType
import com.anarsoft.race.detection.reportbuilder.EventForRunElement
import com.anarsoft.race.detection.util.WithPosition


trait SyncActionEvent[EVENT] extends EventWithType[EVENT] with WithPosition with EventForRunElement {

}
