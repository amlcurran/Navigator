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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class ViewPagerAdapterTest {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Mock
    private Navigator mockNavigator;

    private int currentPage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        viewPager = new ViewPager(Robolectric.application);
        viewPager.setAdapter(new ThreePagerAdapter());
        viewPagerAdapter = new ViewPagerAdapter(viewPager, mockNavigator) {

            @Override
            public void onPageSelected(int selectedPage) {
                super.onPageSelected(selectedPage);
                currentPage = selectedPage;
            }
        };
    }

    @Test
    public void testWhenTheViewPagerIsScrolled_TheAdapterIsNotified() {
        int selectedPage = 2;

        viewPager.setCurrentItem(selectedPage);

        assertEquals(currentPage, selectedPage);
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

        viewPagerAdapter.onNavigated(selectedPage);

        assertEquals(selectedPage, viewPager.getCurrentItem());
    }

    @Test
    public void testWhenTheNavigatorUpdatesTheAdapter_ItIsNotThenUpdatedAboutThisChange() {
        viewPagerAdapter.onNavigated(1);

        verify(mockNavigator, never()).onPageSelected(any(Integer.class));
    }

    private class ThreePagerAdapter extends PagerAdapter {

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

}
