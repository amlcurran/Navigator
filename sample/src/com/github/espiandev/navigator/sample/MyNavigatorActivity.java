package com.github.espiandev.navigator.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class MyNavigatorActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view);
        viewPager.setAdapter(new BasicAdapter());

    }

    private class BasicAdapter extends PagerAdapter {

        private int[] colourArray = new int[] {Color.BLUE,
            Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA };

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
    }
}
