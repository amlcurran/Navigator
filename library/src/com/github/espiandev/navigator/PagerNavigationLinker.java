package com.github.espiandev.navigator;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which maintains an interface between a {@link android.support.v4.view.ViewPager} and a {@link com.github.espiandev.navigator.Navigator},
 * and handles interaction between the two.
 */
public class PagerNavigationLinker {

    private ViewPager viewPager;
    private Navigator navigator;

    public PagerNavigationLinker(ViewPager viewPager, Navigator navigator) {
        this.navigator = navigator;
        this.viewPager = viewPager;
        this.viewPager.setOnPageChangeListener(pageChangeListener);
        navigator.setLinker(this);
    }

    void onNavigationItemSelected(int selectedPage) {
        updateViewPager(selectedPage);
    }

    private void updateViewPager(int selectedPage) {
        viewPager.setOnPageChangeListener(null);
        viewPager.setCurrentItem(selectedPage);
        viewPager.setOnPageChangeListener(pageChangeListener);
    }

    /**
     * Set a {@link android.support.v4.view.PagerAdapter} on the held ViewPager. You must not call
     * this on the ViewPager instance itself as the {@link com.github.espiandev.navigator.Navigator} will not be updated.
     */
    public void setPagerAdapter(PagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
        navigator.bindNavigationItems(buildLabelsList());
    }

    List<CharSequence> buildLabelsList() {
        List<CharSequence> list = new ArrayList<CharSequence>();
        PagerAdapter adapter = viewPager.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            CharSequence adapterBackedTitle = adapter.getPageTitle(i);
            if (TextUtils.isEmpty(adapterBackedTitle)) {
                list.add(getBackupPageTitle(i));
            } else {
                list.add(adapter.getPageTitle(i));
            }
        }
        return list;
    }

    /**
     * Create a title for a page, if none was supplied by the {@link android.support.v4.view.PagerAdapter}.
     * The default implementation returns "Page {number}"
     */
    protected CharSequence getBackupPageTitle(int position) {
        return String.format("Page %d", position);
    }

    private ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            navigator.onPageSelected(position);
        }
    };

}
