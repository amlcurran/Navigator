package com.github.espiandev.navigator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

public class NavigatorTest {

    @Mock
    private PagerNavigationAdapter mockNavigationAdapter;
    private Navigator basicNavigator = new Navigator() {
        @Override
        public void onPageSelected(int selectedPage) {

        }

        @Override
        public void bindNavigationItems(List<CharSequence> labels) {

        }
    };

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnSelectedItem_NotifiesThePagerAdapter() throws Exception {
        int selectedPage = 1;
        basicNavigator.setPagerNavigationAdapter(mockNavigationAdapter);
        basicNavigator.onSelectedItem(selectedPage);

        verify(mockNavigationAdapter).onNavigationItemSelected(selectedPage);
    }



}
