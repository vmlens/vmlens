# Problem

Different Types of Report
    
* Run Report
* Summary
* Non Commutative per Object per Thread
* Report Around Data Race
* Futures/Thread Start Join/Wait
* deadlock warning, potential deadlocks


# Solution

Input -> Transform -> Output

Input: List of Loops containing one or N Runs containing RunElement

ReportFactory : ReportBuilder -> { Transform + Output } : create

TransformAndOutput belong together e.g. specific transforms require specific outputs

# Output

Html
Text

for Non Commutative per Object per Thread first text

# Transform

LoopToUILoop
  -> ElementToUIElement

HTMLOutput
TextOutput
potential: CSVOutput

TextOutput is command like without template
especially for testing the usabilty of a format
probably as input for other tools



