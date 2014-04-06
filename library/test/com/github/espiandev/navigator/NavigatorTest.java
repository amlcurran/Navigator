package com.github.espiandev.navigator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

public class NavigatorTest {

    @Mock
    private PagerNavigationLinker mockNavigationAdapter;
    private Navigator basicNavigator = new Navigator() {
        @Override
        public void onHostNavigated(int selectedPage) {

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
        basicNavigator.setLinker(mockNavigationAdapter);
        basicNavigator.onNavigationItemSelected(selectedPage);

        verify(mockNavigationAdapter).onNavigationItemSelected(selectedPage);
    }

}
