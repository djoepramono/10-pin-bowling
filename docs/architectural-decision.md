# Architectural decision

We can build this bowling application in many different ways. However I'd like to try to adhere to below fundamentals as much as possible.

## Pure Functions

Pure functions are good. It has referential transparency aspect. For the same input you given to a function, it should always return the same result. This makes the application easy to reason about, as it has no side-effects.

For example, the class methods should relies on the parameters to do its job. Or at the very least try to access the class property less. Especially if the properties are mutable.

This also means that, exceptions should be used sparingly. If you are using a function that throws an exception. It'll be good to resolve the exception as quickly as possible.

Last but not least, try to write a function that returns something.

## Towards Microservices

This application is currently a monolith. However, one day you might want to refactor this application to be more "distributed".

For example all functions in the `FrameUtil.java` can ultimately be written off as a separate service e.g. through AWS lambda. `Frame` and `Bowl`properties can be reflected as datas in database tables or CSV files in S3.

Each of this services should only concern about doing 1 job. For example `addBowlToFrame` will exactly just that. Given a frame, the function will add a bowl into that frame and nothing else. Meanwhile, the data layer should be separated from state as much as possible.

## One State Management

Just like any other game, it needs to store a state. For a simple game like this, it's better of putting all possible state in one place. For this application it's in `Game`. This makes it easy to debug the state of the game.

## Logging

Writing to standard out for logging is good. However simply logging without a structure is not. Written logs generally can be categorised as one of these 3 things: info, warning, and error. Thus it's better to flag them appropriately.

For example for an exception logging, we include `ERROR` as part of the log message. This way when we need to visit the log, one day. We can find the errors or warnings easier.

Do not log out some error as `ERROR` and some others as `FAILURE`, unless you have a reason to distinguish them.
