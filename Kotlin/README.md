## Kotlin solution ##

This is a solution of kata in Kotlin language.

## Check solution ##

* To examine a final state of the solution you could checkout branch __exercise__. All other branches that were created during development are merge into that.
* __feature/conjured-items__ branch has an implementation of conjured items processing. It's based on __refactor__ branch
* __refactor__ branch contains only refactoring changes

## Tests

Implementation started from creating tests. Those tests were running for every commit and must be green before commiting.
These tests you could find in final solution if you look for _Legacy_ prefix. After refactoring they became more like integrational tests rather than unit tests. Tests that don't have _Legacy_ prefix were added after a new architecture became more or less final.
Legacy tests are exhaustive to preserve "undefined" behavior that leads to inconsistency  between requirements and actual implementation. For example specification defines range of quality between 0 and 50 but different items behave differently beyond these values.

## Architecture

While solving the kata I tried to apply incremental design technique. So from the very first commit till the end it was not clear which architecture it will be finally. 
Of course some architectural decisions were foreseen. For example it was conscious decision to not separate degradation and updating sellIn value. Despite the S principle in my opinion this separation will bring more overhead than benefits to such a small task. Also items are wrapped in ItemWrappers that are not made immutable due to original nature of GildedRose class contract.  

## PS

While doing this there was not a goal to create ideal architecture but rather make pragmatic solution of a task. 
Have a nice time reviewing code and don't avoid to dig in history of changes!
