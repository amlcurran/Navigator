package com.github.espiandev.navigator;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerNavigatingHost extends NavigatingHost {

    private final ViewPager viewPager;

    public ViewPagerNavigatingHost(ViewPager viewPager) {
        this.viewPager = viewPager;
        this.viewPager.setOnPageChangeListener(pageChangeListener);
    }

    /**
     * Set a {@link android.support.v4.view.PagerAdapter} on the held ViewPager. You must not call
     * this on the ViewPager instance itself as the {@link com.github.espiandev.navigator.Navigator} will not be updated.
     */
    public void setPagerAdapter(PagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
        linker.bindNavigatorItems(buildLabelsList());
    }

    @Override
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

    @Override
    public void onNavigationItemSelected(int selectedPage) {
        updateViewPager(selectedPage);
    }

    void updateViewPager(int selectedPage) {
        viewPager.setOnPageChangeListener(null);
        viewPager.setCurrentItem(selectedPage);
        viewPager.setOnPageChangeListener(pageChangeListener);
    }

    private ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            linker.onNavigatingHostNavigated(position);
        }
    };

}