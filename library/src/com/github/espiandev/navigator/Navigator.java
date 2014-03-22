package com.github.espiandev.navigator;

import java.util.List;

public abstract class Navigator {

    private PagerNavigationAdapter navigationAdapter;

    public abstract void onPageSelected(int selectedPage);

    public abstract void bindNavigationItems(List<CharSequence> labels);

    public void setPagerNavigationAdapter(PagerNavigationAdapter navigationAdapter) {
        this.navigationAdapter = navigationAdapter;
    }

    public void onSelectedItem(int position) {
        navigationAdapter.onNavigationItemSelected(position);
    }

}
