# Solution Approach

## Input and Output

If you read the summary of what this bowling application should do in the README.md, you might find one glaring issue. **There is no clear input and output**.

Sure we are told how the bowling game works and how the score are calculated. But what is the input and output of this application? Should we build a UI out of it? _Which would be great by the way as we can control the input more precisely, e.g. button click than a free text input_. But hey I think it's overkill and I'd choose React.js over Java anytime of the day to build anything UI.

Let's start with the output. There is no explicit rule on what the output should be. So I deliberately choose that the output should be the current score of the game or any log in the console output. No other output shall come out from the application.

Now the inputs. Again there is no explicit rule on what the inputs should be. And this is a bigger headache compared to the output. For example it can be written in the following way
- A sequence of `String` as an argument with a delimiter e.g. `5/ -2 41 35` and so on
- A sequence of `Integer` of knocked out pins e.g. `5 5 0 2 4 1 3 5` and so on

Or maybe it should ask for the user input for every single bowl. Depending on the input type the application can be much simpler or much harder

Based on the a few examples that has expected result. I decide that the input should be a string (_Well, it should be Char really but hey it can be refactored later_) which includes `X`, `/`, and `-` as valid inputs.

Why? Please continue reading.

## Script vs Game

Now we can build a script that processes an input and produces an output. Or should we look further into the horizon. This solution should be versatile enough that it can be easily refactored just in case the requirement change. For example, the application should be an interactive game where users can enter the bowling as they go and see the result as they go, just like real life bowling. If we don't think this far, we might fall into writing an application that is super hard to refactor later.

This is why I decided to create the actual application in `Game.java` which main method is accepting a single string. This is the lowest common denominator to accept any kind of input. Say that the input now should be coming from a CSV file. This solution can be easily refactored to handle that.

When it accepts string, it has a superior flexibility and connectivity. Though of course it needs a superior validation as well.

## Separate "Building the Line" and "Getting the Score"

Now that we have more decisions lined out. We can think more about the game itself. Aside from the input and output, we need to identify the game structure especially the data, state, and functions. You can read more on the architectural-decision.md to know more about what I think about those three.

After some thought, there is one extra approach that I decide to take while building the solution. Separate *building the line* and *getting the score*. So the score doesn't get calculated as the bowls are rolled in the same function. But rather it's actually two separate functions that are can be called one after another if needs to.

Why? Well mainly because I am not a bowler myself and I don't actually know how the scores are calculated, until I see this exercise. Thus I want to separate the concern, just in case I got the calculation logic wrong (which I did), I can still at least get the building part works.

## Functional vs Imperative

I personally prefer functional approach when writing a program. Unfortunately while Java 8+ is great, it is not as functional as Scala. Thus there are quite a few functional code that becomes somewhat long and un-intuitive. This exercise has a rule that we cannot use outside library when writing the application. Thus it is quite a journey if we want to write this application using JAVA in functional way.

Thus I opt to write code in functional way sparingly (_still there is a number of them_), all while embracing Java way of coding, for example using Exception. Afterall, readability is more important than shorter code.
