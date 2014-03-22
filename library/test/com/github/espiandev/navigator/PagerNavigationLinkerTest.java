package com.github.espiandev.navigator;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class PagerNavigationLinkerTest {

    private ViewPager viewPager;
    private PagerNavigationLinker pagerNavigationLinker;

    @Mock
    private Navigator mockNavigator;

    private int currentPage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        viewPager = new ViewPager(Robolectric.application);
        viewPager.setAdapter(new ThreePagerNotTitledAdapter());
        pagerNavigationLinker = new PagerNavigationLinker(viewPager, mockNavigator);
    }

    @Test
    public void testWhenTheViewPagerIsScrolled_TheNavigatorIsNotified() {
        int selectedPage = 2;

        viewPager.setCurrentItem(selectedPage);

        verify(mockNavigator).onPageSelected(selectedPage);
    }

    @Test
    public void testWhenTheNavigatorUpdatesTheAdapter_TheViewPagerScrollsToTheUpdatedPosition() {
        int selectedPage = 1;

        pagerNavigationLinker.onNavigationItemSelected(selectedPage);

        assertEquals(selectedPage, viewPager.getCurrentItem());
    }

    @Test
    public void testWhenTheNavigatorUpdatesTheAdapter_ItIsNotThenUpdatedAboutThisChange() {
        pagerNavigationLinker.onNavigationItemSelected(1);

        verify(mockNavigator, never()).onPageSelected(any(Integer.class));
    }

    @Test
    public void testWhenSetAdapterIsCalled_TheAdapterIsPassedToTheViewPager() {
        PagerAdapter fourPagerAdapter = new FourPagerTitledAdapter();
        pagerNavigationLinker.setPagerAdapter(fourPagerAdapter);

        assertEquals(fourPagerAdapter, viewPager.getAdapter());
    }

    @Test
    public void testWhenSetAdapterIsCalled_TheNavigatorIsUpdatedAboutLabels() {
        PagerAdapter fourPagerAdapter = new FourPagerTitledAdapter();
        pagerNavigationLinker.setPagerAdapter(fourPagerAdapter);

        verify(mockNavigator).bindNavigationItems(any(List.class));
    }

    @Test
    public void testBuildLabels_PullsLabelsFromTheViewPagerAdapter() {
        PagerAdapter adapter = new FourPagerTitledAdapter();
        pagerNavigationLinker.setPagerAdapter(adapter);

        List<CharSequence> labelsList = pagerNavigationLinker.buildLabelsList();

        assertEquals(adapter.getCount(), labelsList.size());
        for (int i = 0; i < adapter.getCount(); i++) {
            assertEquals(adapter.getPageTitle(i), labelsList.get(i));
        }
    }

    @Test
    public void testIfTheAdapterDoesntSupplyLabels_ThenWeMakeLabelsInstead() {
        PagerAdapter adapter = new ThreePagerNotTitledAdapter();
        pagerNavigationLinker.setPagerAdapter(adapter);

        List<CharSequence> labelsList = pagerNavigationLinker.buildLabelsList();

        assertEquals(adapter.getCount(), labelsList.size());
        for (int i = 0; i < adapter.getCount(); i++) {
            assertNotNull(labelsList.get(i));
            assertEquals(pagerNavigationLinker.getBackupPageTitle(i), labelsList.get(i));
        }
    }

    private class ThreePagerNotTitledAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

    }

    private class FourPagerTitledAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "page";
        }
    }

}
