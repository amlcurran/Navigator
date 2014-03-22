Navigator
=========

Quick library that aids creating navigation around a ViewPager

Why?
====

A very common design pattern in Android is to have Action Bar tabs with a ViewPager. Whilst this is great, it requires a lot of boilerplate code to get up and running (including listeners, generating tabs and so on). And that's no fun. Plus, what happens if you suddenly get the bug to jump on the [hipsternav](http://developer.android.com/training/implementing-navigation/nav-drawer.html) trend? You have to redo all your logic again. But, with this library, we've tried to make this as quite and painless as possible. No boilerplate!

How?
====

    Navigator tabNavigation = new TabNavigator(this);
    PagerNavigationLinker pagerNavigationLinker = new PagerNavigationLinker(viewPager, tabNavigation);
    pagerNavigationLinker.setPagerAdapter(new BasicAdapter());
    
That's it. Genuinely everything! You'll get some nice tabs, and when you swipe between your pages, they'll update. And when you click on a tab, your ViewPager will scroll to the right page. Magic!

