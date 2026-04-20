# Problem

When a pre-analyzed method enter gets called all following method calls except for callbacks should be filtered.

# Solution

extra method in CallbackAction
executeAndCheckPreAnalyzedFilter()

on enter:
check and push on stack

on exit:
pop, and check


check: only one atomic followed by calback


