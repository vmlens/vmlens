We do not need a stack, since the set state and trigger
function are always directly one after the other:

beforeMethodCall
methodEnter

methodExit
afterMethodCall


beforeField...
afterFieldAccess

Open: Exception Handling


We need the position for loop calculation