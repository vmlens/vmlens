package com.anarsoft.race.detection.process.run

import com.vmlens.report.assertion.{OnDescriptionAndLeftBeforeRight, OnEvent}

class ProcessRunContext(val onTestLoopAndLeftBeforeRight : OnDescriptionAndLeftBeforeRight,
                        val onEvent : OnEvent,
                        val showAllMemoryAccess : Boolean,
                        val showAllRuns : Boolean) {

}
