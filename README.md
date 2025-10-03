# Splatfest Multiplier Battle Odds
----------------------------------------------------------
### How 333x Battles Work
The basic rundown of a 333x vs 100x, is after both teams randomly generate a number, should either one fall into the alloted amount from their specific team's accumulated festival shall value, then it sets the other teams festival shells to the maximum of 7 per player, and then re-checks (not re-roll) to see if the new amount holds the value generated for their team. If it does, then a 333x battle is triggered. This amounts to a 7% for a 333x battle to occur, given a 100x battle spawned from the opposing team.

### What Can Be Modified
A simulation that runs until a certain amount of 333x battles are hit. This value can be adjusted quite easily.
We can also adjust the value of a festival shell. From my recorded data, that value sits around 0.25% per shell added to the team total, which is then used to determine if a given match will trigger a 100x from that team.
Other editable values include the average amount of shells gained per match, currently 1/24 matches per shell (according to my data it is roughly an 8.5% chance that a match will be a 10x, given that it isn't a 100x or 333x battle). Assuming we win 50% of the 10x games, it is fair to have an increase of 1/24 shell value per player per match.

The team A shell average displayed at the end, is the average amount of shells team a has per 100x. This is equal to team b, as we must assume each team has the same amount of shells on a long term average. Plus there is no way to tell how many shells the opposing team has.