# Problem

Process the n types of interleave actions which have different dependencies on the other 
same interleave action.


# Dynamic

AlternatingOrderContainerFactory -> creates: KeyToOperationCollection 


# Structure

KeyToOperationCollection ->  {  volatileAccess,
                                lockAndConditions,
                                barrier,
                                independentActions }

# Groups

 * independent
 * dependent
   * volatila
   * barriers
 * locks
