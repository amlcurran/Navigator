package com.github.espiandev.navigator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class PagerNavigationLinkerTest {

    private PagerNavigationLinker pagerNavigationLinker;

    @Mock
    private Navigator mockNavigator;
    @Mock
    private NavigatingHost mockNavigatingHost;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        pagerNavigationLinker = new PagerNavigationLinker(mockNavigatingHost, mockNavigator);
    }

    @Test
    public void whenANavigationItemIsSelected_TheHostIsUpdated() {
        int selectedPage = 2;

        pagerNavigationLinker.onNavigationItemSelected(selectedPage);

        verify(mockNavigatingHost).onNavigationItemSelected(selectedPage);
    }

    @Test
    public void whenTheHostHasBeenNavigatedOn_TheNavigatorIsUpdated() {
        int selectedPage = 2;

        pagerNavigationLinker.onNavigatingHostNavigated(selectedPage);

        verify(mockNavigator).onHostNavigated(selectedPage);
    }

}
