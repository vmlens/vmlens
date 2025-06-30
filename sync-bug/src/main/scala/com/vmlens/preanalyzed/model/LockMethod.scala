package com.vmlens.preanalyzed.model

import com.vmlens.preanalyzed.model.lockoperation.LockOperation

case class LockMethod(name : String, desc : String, lockOperation : LockOperation)
