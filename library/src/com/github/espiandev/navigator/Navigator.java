package com.github.espiandev.navigator;

public abstract class Navigator {

    private PagerNavigationAdapter navigationAdapter;

    public abstract void onPageSelected(int selectedPage);

    public void setPagerNavigationAdapter(PagerNavigationAdapter navigationAdapter) {
        this.navigationAdapter = navigationAdapter;
    }

    public void onSelectedItem(int position) {
        navigationAdapter.onNavigationItemSelected(position);
    }

}
