package com.github.espiandev.navigator;

import java.util.List;

public abstract class NavigatingHost {
    protected PagerNavigationLinker linker;

    public abstract List<CharSequence> buildLabelsList();

    /**
     * Create a title for a page, if none was supplied by the {@link android.support.v4.view.PagerAdapter}.
     * The default implementation returns "Page {number}"
     */
    protected CharSequence getBackupPageTitle(int position) {
        return String.format("Page %d", position);
    }

    void setLinker(PagerNavigationLinker linker) {
        this.linker = linker;
    }

    /**
     * Called when the user has selected a navigation item and the host should be updated to reflect this
     */
    protected abstract void onNavigationItemSelected(int selectedPage);

    protected void onNavigatingHostNavigated(int selectedPage) {
        linker.onNavigatingHostNavigated(selectedPage);
    }
}
