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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class TabNavigatorTest {

    private Activity activity;
    private Navigator navigator;
    private ActionBar actionBar;

    @Mock
    PagerNavigationLinker mockLinker;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        activity = Robolectric.buildActivity(Activity.class).create().start().resume().get();
        navigator = new TabNavigator(activity);
        actionBar = activity.getActionBar();
    }

    @Test
    public void testTheActionBar_IsSetToChoiceModeOfTabs() {
        assertEquals(ActionBar.NAVIGATION_MODE_TABS, actionBar.getNavigationMode());
    }

    @Test
    public void testBindNavigationItems_AddsItemsToTheTabBar() {
        List<CharSequence> exampleNavigationItems = ListViewNavigatorTest.createNavLabels();

        navigator.bindNavigationItems(exampleNavigationItems);

        assertEquals(exampleNavigationItems.size(), actionBar.getTabCount());
    }

    @Test
    public void testBindNavigationItems_AddsTheCorrectItemsToTheTabBar() {
        List<CharSequence> exampleNavigationItems = ListViewNavigatorTest.createNavLabels();

        navigator.bindNavigationItems(exampleNavigationItems);

        for (int i = 0; i < exampleNavigationItems.size(); i++) {
            assertEquals(exampleNavigationItems.get(i), actionBar.getTabAt(i).getText());
        }
    }

    @Test
    public void testBindNavigationItems_DoesntKeepPreviousItems() {
        List<CharSequence> exampleNavigationItems = ListViewNavigatorTest.createNavLabels();

        navigator.bindNavigationItems(exampleNavigationItems);
        navigator.bindNavigationItems(exampleNavigationItems);

        assertEquals(exampleNavigationItems.size(), actionBar.getTabCount());
        assertEquals(actionBar.getTabAt(1).getText(), exampleNavigationItems.get(1));
    }

    @Test
    public void testWhenAPageIsSelected_TheCorrespondingTabIsSelected() {
        List<CharSequence> exampleNavigationItems = ListViewNavigatorTest.createNavLabels();
        int selectedPage = 2;

        navigator.bindNavigationItems(exampleNavigationItems);
        navigator.onPageSelected(selectedPage);

        assertEquals(actionBar.getSelectedTab().getPosition(), selectedPage);
    }

    @Test
    public void testWhenAnItemIsClickedOnTheListView_ThePagerAdapterIsUpdated() {
        List<CharSequence> exampleNavigationItems = ListViewNavigatorTest.createNavLabels();
        int selectedListItem = 2;

        navigator.setPagerNavigationAdapter(mockLinker);
        navigator.bindNavigationItems(exampleNavigationItems);
        actionBar.selectTab(actionBar.getTabAt(selectedListItem));

        verify(mockLinker).onNavigationItemSelected(selectedListItem);
    }

}
