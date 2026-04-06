# Problem

When a pre-analyzed method enter gets called all following method calls except for callbacks should be filtered.

# Solution

extra method in CallbackAction
executeAndCheckPreAnalyzedAction()

on enter:
check and push on stack

on exit:
pop, anc check


check: only one atomic followed by calback


