package com.github.espiandev.navigator;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class FragmentTransactionNavigatingHost extends NavigatingHost {

    private FragmentPagerAdapter fragmentList;
    private FragmentManager fragmentManager;
    private int fragmentFrameId;

    public FragmentTransactionNavigatingHost(FragmentManager fragmentManager, int fragmentFrameId) {
        this.fragmentManager = fragmentManager;
        this.fragmentFrameId = fragmentFrameId;
    }

    public void setPagerAdapter(FragmentPagerAdapter pagerAdapter) {
        fragmentList = pagerAdapter;
        linker.bindNavigatorItems(buildLabelsList());
    }

    @Override
    public List<CharSequence> buildLabelsList() {
        List<CharSequence> list = new ArrayList<CharSequence>();
        PagerAdapter adapter = fragmentList;
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
    protected void onNavigationItemSelected(int selectedPage) {
        fragmentManager.beginTransaction()
                .replace(fragmentFrameId, fragmentList.getItem(selectedPage))
                .commit();
    }
}
