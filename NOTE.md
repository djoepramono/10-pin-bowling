# Notes

- Java 8+ is great, but still it is not as functional as Scala
- Embrace Java way of coding, instead of forcing the Scala way
- Readability is more important than shorter code

# What's not clear

## What is the input method?

Is the input method a string containing the score separated by spaces?
Or is the input method is actually a continuous entry of score entered one by one?

For my solution, I assume the correct input method is the former one but I would take into consideration
that the solution should be easily be extended to handle the second input method.


## Hows the potential errors are handled?

Should they throw an exception and died?
Should they silently fail?
Should they log a warning output but continue on?

The answer is depends ...
