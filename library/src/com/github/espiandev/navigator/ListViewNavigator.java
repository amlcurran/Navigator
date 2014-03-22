package com.github.espiandev.navigator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ListViewNavigator extends Navigator {

    private final NavigatorAdapter listAdapter;
    private ListView listView;

    public ListViewNavigator(Context context, ListView listView) {
        this.listAdapter = new NavigatorAdapter(context, android.R.layout.simple_list_item_activated_1);
        this.listView = listView;
        this.listView.setAdapter(listAdapter);
    }

    @Override
    public void onPageSelected(int selectedPage) {

    }

    @Override
    public void bindNavigationItems(List<CharSequence> labels) {
        listAdapter.clear();
        listAdapter.addAll(labels);
        listAdapter.notifyDataSetChanged();
    }

    public class NavigatorAdapter extends ArrayAdapter<CharSequence> {

        public NavigatorAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_activated_1, parent, false);
            }

            ((TextView) convertView).setText(getItem(position));

            return convertView;
        }
    }
}
