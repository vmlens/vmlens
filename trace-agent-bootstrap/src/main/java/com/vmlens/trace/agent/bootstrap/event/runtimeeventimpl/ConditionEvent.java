package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.gen.ConditionEventGen;

/**
 * a condition has the lock object hash code for the happens before relations
 * and the condition object hash code for the notify interleave action
 * we do not need the condition object hash code for the happens before calculation
 *
 */

public class ConditionEvent extends ConditionEventGen {
}
