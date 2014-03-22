package com.github.espiandev.navigator;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * {@link com.github.espiandev.navigator.Navigator} that uses a ListView. This is ideal for creating a {@link android.support.v4.widget.DrawerLayout}-based navigation model.
 */
public class ListViewNavigator extends Navigator {

    private final ArrayAdapter<CharSequence> adapter;
    private ListView listView;

    public ListViewNavigator(Context context, ListView listView) {
        this(listView, new ArrayAdapter<CharSequence>(context, android.R.layout.simple_list_item_activated_1));
    }

    public ListViewNavigator(ListView listView, ArrayAdapter<CharSequence> adapter) {
        this.adapter = adapter;
        this.listView = listView;
        this.listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onPageSelected(int selectedPage) {
        this.listView.setItemChecked(selectedPage, true);
    }

    @Override
    public void bindNavigationItems(List<CharSequence> labels) {
        listView.setAdapter(adapter);
        adapter.clear();
        adapter.addAll(labels);
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onNavigationItemSelected(position);
        }
    };

}
