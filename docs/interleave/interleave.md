# Problem

Package structure
Complicated logic and some state handling.

# Structure

state:
    run
    loop
    alternatingorder
    threadindexcollection

data:
    interleaveaction

process:
    buildalternatingorder
    buildcalculatedrun


# Dynamic

client(actualRun) -> loop(actualRun,state)   {  actualRun         ->   buildalternatingorder(actualRun)
                                                alternatingorder  ->   buildcalculatedrun(alternatingorder) }
                           




