# Football World Cup Score Board

## Assumptions:
- Scoreboard is a library that is responsible for summary generation of currently played matches.
- Scoreboard library does not need to store the information about the way data/matches change - the end user is solely interested in aggregated view of it.
- Both the summary view and ranking (the order in which matches appear in summary) is updated/calculated eagerly, making it much quicker to access for the lib's user. 
- Library's code is built around domain class called `Match`.
- Match is identified by `UUID`, teams identified by `String`.
- Finishing a match deletes it from the library. Based on library use case (or responsibility), that data won't be ever used/needed.
- Commands (or Events) that are executed on scoreboard come in right order. Based on this assumptions, few decisions around validation have been made:
  - updating scores lack any validation - update from `X 1 - Y 2` to `X 0 - Y 0` is considered valid,
  - updating not started match results in error,
  - deleting not existing match results in error.
- Is not "thread-safe". Although implementing repository in the concurrent safe manner could help, the assumption around events' order must remain valid.
  
