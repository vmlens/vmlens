# Problem

Different Types of Report
    
* Run Report
* Summary
* Non Commutative per Object per Thread
* Report Around Data Race
* only Futures/Thread Start Join/Wait 
* deadlock warning, potential deadlocks
* Filter only once accessed elements


# Solution

1. collect run data
2. add description delegates to DescriptionCallbackImpl
3. create report overview
4. delegate to RunReportFactory for each run
   5. additional report for field access on stacktrace (txt based on graph)

if we want to combine multiple test runs we can combine 3 and 4


Chaining of three builder:
LoopResultCallbackImpl -> Tuple { DescriptionCallback , ReportBuilder }  


LoopResultCallbackImpl:





