public class Problems {

/*
Amazon sellers can bundle any combination of their products for a promotional offer.
Given a seller's product catalogue, generate all possible bundles.
A bundle can have any number of products — including an empty bundle (no promotion) or all products together.

i/p - products = [5, 2, 4]   // product IDs

Output -
[5, 2, 4]
[5, 2]
[5, 4]
[5]
[2, 4]
[2]
[4]
[]

Constraints:
All product IDs are unique
Empty bundle is valid
Order of elements in each bundle must match original catalogue order
 */



/*
A payments fraud team is investigating suspicious activity.
They have a list of transaction amounts made by a user in a single day.
Find all combinations of transactions that add up to exactly ₹K — each transaction can only be used once.
This helps identify if a large amount was deliberately split across multiple smaller transactions to avoid detection.

i/p: transactions = [1000, 2000, 3000, 4000, 5000]
K = 5000

o/p:
[1000, 4000]
[2000, 3000]
[5000]

Constraints:
Each transaction can be used only once
Print all valid combinations, not just one
Order within each combination must match original order
 */

/*
A payments risk engine needs to flag accounts where a suspicious amount appears too many times across transaction combinations.
Given a list of transaction amounts, count how many unique subsets of transactions sum to exactly ₹K.
If the count exceeds a threshold, the account is flagged for review.

i/p:
transactions = [1000, 2000, 3000, 1000, 2000]
K = 3000

o/p:
3
reason:
[1000, 2000]  → indices 0,1 → sum = 3000 ✓
[3000]        → index 2     → sum = 3000 ✓
[1000, 2000]  → indices 3,4 → sum = 3000 ✓
Total = 3 combinations

Constraints:
Elements can repeat (same transaction amount appearing multiple times)
Count each unique index combination separately
Return only the count, not the combinations themselves

 */


/*
Amazon is building a "spend exactly" feature for gift cards.
A customer has a gift card worth exactly ₹K.
Given a list of available product prices, find all unique combinations of products they can buy to spend the gift card completely.
A product can be purchased multiple times if needed.

i/p: prices  = [10, 20, 30]
K = 30

o/p:
[10, 10, 10]
[10, 20]
[30]

Constraints:

Same product can be picked multiple times
Each combination must be unique (no duplicates like [10,20] and [20,10])
Order within combination doesn't matter — list in ascending order
 */
}
