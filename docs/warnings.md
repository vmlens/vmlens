# Feature

Warning per loop and per run.

# Structure

warning per loop or per run
for the time being warning peer loop have runid = 0

serializab le class:
    loopid
    runid
    messageId
    firstParam

# Display

all occoured warnings will be displayed
some, e.g. blocked influence the selection of a run, boolean flag shouldBeShownBecauseOfWarning
all other not

# Implementation

Constants only in bootstrap
converting from constant to scala classes in sync bug
here we also store the labels

