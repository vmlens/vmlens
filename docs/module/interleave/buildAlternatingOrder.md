# Problem

Process the n types of interleave actions which have different dependencies on the other 
same interleave action.


# Dynamic

AlternatingOrderContainerFactory -> creates: KeyToOperationCollection 


# Structure

KeyToOperationCollection ->  {  independentActions , KeyToOperation   } 
   -KeyToOperation->  { volatileAccess , lockAndConditions, barrier  }

# Groups

 * independent
 * dependent
   * volatile
   * barriers
   * locks

