/*
 * Copyright 2014 Alex Curran
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.espiandev.navigator;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;

import java.util.List;

public class TabNavigator extends Navigator {

    private final ActionBar actionBar;

    public TabNavigator(Activity activity) {
        this.actionBar = activity.getActionBar();
        this.actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    }

    @Override
    public void onPageSelected(int selectedPage) {
        actionBar.selectTab(actionBar.getTabAt(selectedPage));
    }

    @Override
    public void bindNavigationItems(List<CharSequence> labels) {
        actionBar.removeAllTabs();
        for (CharSequence charSequence : labels) {
            actionBar.addTab(actionBar.newTab()
                    .setText(charSequence)
                    .setTabListener(tabListener));
        }
    }

    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            onNavigationItemSelected(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    };

}
