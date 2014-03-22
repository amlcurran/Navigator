package com.github.espiandev.navigator;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class PagerNavigationAdapter extends ViewPager.SimpleOnPageChangeListener {

    private ViewPager viewPager;
    private Navigator[] navigators;

    public PagerNavigationAdapter(ViewPager viewPager, Navigator... navigators) {
        this.navigators = navigators;
        this.viewPager = viewPager;
        this.viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageSelected(int selectedPage) {
        for (Navigator navigator : navigators) {
            navigator.onPageSelected(selectedPage);
        }
    }

    public void onNavigationItemSelected(int selectedPage) {
        updateViewPager(selectedPage);
    }

    private void updateViewPager(int selectedPage) {
        viewPager.setOnPageChangeListener(null);
        viewPager.setCurrentItem(selectedPage);
        viewPager.setOnPageChangeListener(this);
    }

    public void setPagerAdapter(PagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
        for (Navigator navigator : navigators) {
            navigator.bindNavigationItems(buildLabelsList());
        }
    }

    public List<CharSequence> buildLabelsList() {
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

    public CharSequence getBackupPageTitle(int position) {
        return String.format("Page %d", position);
    }
}
