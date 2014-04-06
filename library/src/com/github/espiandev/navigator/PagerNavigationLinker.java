package com.github.espiandev.navigator;

import java.util.List;

/**
 * Class which maintains an interface between a {@link android.support.v4.view.ViewPager} and a {@link com.github.espiandev.navigator.Navigator},
 * and handles interaction between the two.
 */
public class PagerNavigationLinker {

    private final NavigatingHost navigatingHost;
    private final Navigator navigator;

    public PagerNavigationLinker(NavigatingHost navigatingHost, Navigator navigator) {
        this.navigator = navigator;
        this.navigatingHost = navigatingHost;
        this.navigatingHost.setLinker(this);
        this.navigator.setLinker(this);
    }

    void onNavigationItemSelected(int selectedPage) {
        navigatingHost.onNavigationItemSelected(selectedPage);
    }

    public void onNavigatingHostNavigated(int selectedPage) {
        navigator.onHostNavigated(selectedPage);
    }

    public void bindNavigatorItems(List<CharSequence> charSequences) {
        navigator.bindNavigationItems(charSequences);
    }
}
