package com.github.espiandev.navigator.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.espiandev.navigator.ListViewNavigator;
import com.github.espiandev.navigator.Navigator;
import com.github.espiandev.navigator.PagerNavigationAdapter;

public class MyNavigatorActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view);
        ListView navListView = (ListView) findViewById(R.id.listView);

        Navigator listNavigator = new ListViewNavigator(this, navListView);
        Navigator toastNavigator = new ToastNavigator(this);

        PagerNavigationAdapter pagerNavigationAdapter = new PagerNavigationAdapter(viewPager, listNavigator, toastNavigator);
        pagerNavigationAdapter.setPagerAdapter(new BasicAdapter());

    }

    private class BasicAdapter extends PagerAdapter {

        private int[] colourArray = new int[] {Color.BLUE,
            Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA };
        private String[] titleArray = new String[] { "Blue", "Red", "Green", "Yellow", "Magenta" };

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o.equals(view);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View item = new View(MyNavigatorActivity.this);
            item.setBackgroundColor(colourArray[position]);
            container.addView(item);
            return item;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleArray[position];
        }
    }
}
