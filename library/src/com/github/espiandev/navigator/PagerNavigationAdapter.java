package com.github.espiandev.navigator;

import android.support.v4.view.ViewPager;

public class PagerNavigationAdapter extends ViewPager.SimpleOnPageChangeListener {

    private ViewPager viewPager;
    private Navigator navigator;

    public PagerNavigationAdapter(ViewPager viewPager, Navigator navigator) {
        this.navigator = navigator;
        this.viewPager = viewPager;
        this.viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageSelected(int selectedPage) {
        navigator.onPageSelected(selectedPage);
    }

    public void onNavigated(int selectedPage) {
        updateViewPager(selectedPage);
    }

    private void updateViewPager(int selectedPage) {
        viewPager.setOnPageChangeListener(null);
        viewPager.setCurrentItem(selectedPage);
        viewPager.setOnPageChangeListener(this);
    }
}
