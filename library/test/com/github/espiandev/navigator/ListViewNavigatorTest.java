package com.github.espiandev.navigator;

import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class ListViewNavigatorTest {

    private ListView listView;
    private ListViewNavigator navigator;

    @Mock
    private PagerNavigationLinker mockPagerAdapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        listView = new ListView(Robolectric.application);
        navigator = new ListViewNavigator(Robolectric.application, listView);
    }

    @Test
    public void testANavigationAdapterIsSetOnTheListView() {
        assertTrue(listView.getAdapter() instanceof ListViewNavigator.NavigatorAdapter);
    }

    @Test
    public void testBindNavigationItems_AddsItemsToTheAdapter() {
        List<CharSequence> exampleNavigationItems = new ArrayList<CharSequence>();
        exampleNavigationItems.add("one");
        exampleNavigationItems.add("two");
        exampleNavigationItems.add("three");

        navigator.bindNavigationItems(exampleNavigationItems);

        assertEquals(listView.getAdapter().getItem(1), exampleNavigationItems.get(1));
    }

    @Test
    public void testBindNavigationItems_DoesntKeepPreviousItems() {
        List<CharSequence> exampleNavigationItems = new ArrayList<CharSequence>();
        exampleNavigationItems.add("one");
        exampleNavigationItems.add("two");
        exampleNavigationItems.add("three");

        navigator.bindNavigationItems(exampleNavigationItems);
        navigator.bindNavigationItems(exampleNavigationItems);

        assertEquals(exampleNavigationItems.size(), listView.getAdapter().getCount());
        assertEquals(listView.getAdapter().getItem(1), exampleNavigationItems.get(1));
    }

    @Test
    public void testWhenAPageIsSelected_TheListViewSetsThatItemChecked() {
        int selectedPage = 2;

        navigator.onPageSelected(selectedPage);

        assertEquals(listView.getCheckedItemPosition(), selectedPage);
    }

    @Test
    public void testWhenAnItemIsClickedOnTheListView_ThePagerAdapterIsUpdated() {
        List<CharSequence> exampleNavigationItems = new ArrayList<CharSequence>();
        exampleNavigationItems.add("one");
        exampleNavigationItems.add("two");
        exampleNavigationItems.add("three");
        int selectedListItem = 2;

        navigator.bindNavigationItems(exampleNavigationItems);
        navigator.setPagerNavigationAdapter(mockPagerAdapter);
        listView.performItemClick(null, selectedListItem, selectedListItem);

        verify(mockPagerAdapter).onNavigationItemSelected(selectedListItem);
    }

}
