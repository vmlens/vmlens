package com.anarsoft.race.detection.createpartialordersyncaction

import com.anarsoft.race.detection.groupinterleave.InterleaveEvent
import com.anarsoft.race.detection.processeventbytype.WithCompareType


trait SyncActionEventWithCompareType[EVENT] extends InterleaveEvent with WithCompareType[EVENT] {

}
