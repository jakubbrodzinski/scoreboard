
## Overcomplicated approach:
- Separation between the domain code and some adapters i.e. Repositories
- The solution contain two main "entitites": 
  - _Match_ - that is a sequence of events
  - _Score_ - that is simplfied, precalculated 
- Regarding the last requirement:
  - _total score_ is the sum of scored goals in a match by both teams.

### I shouldn't be worried about the match at all. My responsibility is solely a scoreboard part.


## Simplfied #2 approach
- Match identified by ID,
- Teams identified by Strings.
- Can just remove finished match - from scoreboard perspective, we don't need it.
- Went w/ more anemic model, where most of the 
- No validation of scores - this should happen somewhere else - scoreboard is an end client.
  - Nope - by making a command Update Score (instead of for example `Goal Score` i would need some validation, right?)