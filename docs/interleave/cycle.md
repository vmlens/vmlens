# Problem

Through cycles, e.g. impossible alternating orders the calculating of the next order takes very long.


# Solution

## Alternative A: Improved Filter

(0,3) < (1,7)
(0,2) > (1,4)

o.k.

(0,2) > (1,9)
(0,3) < (1,7)

n.o.k

take all for index 0
create right after left for inverse
sort by position
if current smaller min value for this thread index there is a loop

if we have done this for thread index 0 we do not have to look for thread index 1, e.g symmetric result

## Alternative B: check during creation of alternating order


## Existing Filter
The existing filter must still be used for example for more complex cycles 


