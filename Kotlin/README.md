## Kotlin solution ##

This is a solution of kata in Kotlin language.

While solvin this I tried to apply incremental design technique. 
So from the very first commit till the end it was not clear which architecture it will be finally. 
Of course I had some big picture upfront. For example it was concious decision to not separate degradation and updating sellIn value. 
Despite the S principle in my opinion this separation will bring more overhead then benefits to such a small task. 
Also items are wrapped in ItemWrappers that are not made immutable due to original nature of GildedRose class contract. 
