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

import java.util.List;

/**
 * Abstract class which represents a control that handles navigation between pages on a ViewPager,
 * for example ActionBar tabs or a hipster-nav drawer.
 */
public abstract class Navigator {

    private PagerNavigationLinker navigationAdapter;

    /**
     * Called when the {@link android.support.v4.view.ViewPager} has scrolled to another page. Use this method to update your navigation control
     * @param selectedPage the page scrolled to
     */
    protected abstract void onHostNavigated(int selectedPage);

    /**
     * Called when the {@link android.support.v4.view.ViewPager} has a list of pages and these should be fed into a navigation model. The Navigator
     * should therefore render these into the appropriate UI
     * @param labels list of the pages which the {@link android.support.v4.view.ViewPager} contains
     */
    protected abstract void bindNavigationItems(List<CharSequence> labels);

    void setLinker(PagerNavigationLinker navigationAdapter) {
        this.navigationAdapter = navigationAdapter;
    }

    /**
     * Call this method when your navigation control has selected an item and wishes to notify the ViewPager
     * @param position the position of the selected item
     */
    protected void onNavigationItemSelected(int position) {
        navigationAdapter.onNavigationItemSelected(position);
    }

}
