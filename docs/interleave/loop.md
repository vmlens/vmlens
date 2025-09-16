# Scenario

Extension, that VMLens works with loops.

# Distribution

## Run

actual run:
detect also non interleave action, while loop with data race
if stands on x after n runs change to y
to detect a loop standard must be to keep on the current thread

calculated run
calculated run -> list( thread index or loop(size,thred index))
if not thread index, e.g. blocked try to keep on the current thread

## build loop 

per thread check for loops and replace interleave actions with loop 
[loop] one iteration of loop

## compare alternating order

loop independent of size are equal
which means that loop of size 0 are new alternating orders