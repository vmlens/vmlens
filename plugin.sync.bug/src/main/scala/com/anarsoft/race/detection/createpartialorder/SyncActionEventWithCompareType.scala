package com.anarsoft.race.detection.createpartialorder

import com.anarsoft.race.detection.groupsyncaction.SyncActionEvent
import com.anarsoft.race.detection.processeventbytype.WithCompareType


trait SyncActionEventWithCompareType[EVENT] extends SyncActionEvent with WithCompareType[EVENT] {

}
