package com.github.espiandev.navigator;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class PagerNavigationLinker {

    private ViewPager viewPager;
    private Navigator navigator;

    public PagerNavigationLinker(ViewPager viewPager, Navigator navigator) {
        this.navigator = navigator;
        this.viewPager = viewPager;
        this.viewPager.setOnPageChangeListener(pageChangeListener);
        navigator.setPagerNavigationAdapter(this);
    }

    void onNavigationItemSelected(int selectedPage) {
        updateViewPager(selectedPage);
    }

    private void updateViewPager(int selectedPage) {
        viewPager.setOnPageChangeListener(null);
        viewPager.setCurrentItem(selectedPage);
        viewPager.setOnPageChangeListener(pageChangeListener);
    }

    public void setPagerAdapter(PagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
        navigator.bindNavigationItems(buildLabelsList());
    }

    protected List<CharSequence> buildLabelsList() {
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
